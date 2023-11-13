package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.adapter.out.persistence.QAuctionEntity;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.enums.AuctionState;
import com.mysql.cj.util.StringUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.mini.auction.auction.adapter.out.persistence.QAuctionEntity.auctionEntity;
import static com.mini.auction.auction.adapter.out.persistence.QCommentsEntity.commentsEntity;
import static com.mini.auction.member.adapter.out.persistence.QMemberEntity.memberEntity;

@RequiredArgsConstructor
class AuctionCustomRepositoryImpl implements AuctionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void updateDetail(String id, AuctionDetail detail) {
        jpaQueryFactory.update(auctionEntity)
            .set(auctionEntity.title, detail.getTitle())
            .set(auctionEntity.contents, detail.getContents())
            .set(auctionEntity.openDateTime, detail.getOpenDateTime())
            .set(auctionEntity.closedDateTime, detail.getClosedDateTime())
            .set(auctionEntity.minimumBidAmount, detail.getMinimumBidAmount().getAmount())
            .set(auctionEntity.updateDateTime, LocalDateTime.now())
            .where(auctionEntity.id.eq(id))
            .execute();
    }

    @Override
    public AuctionRes getAuctionDetailById(String id) {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    AuctionRes.class,
                    auctionEntity.sellerId,
                    auctionEntity.createDateTime,
                    Projections.constructor(
                        AuctionDetail.class,
                        auctionEntity.title,
                        auctionEntity.contents,
                        auctionEntity.openDateTime,
                        auctionEntity.closedDateTime,
                        auctionEntity.minimumBidAmount
                    )
                )
            ).from(auctionEntity)
            .where(auctionEntity.id.eq(id).and(auctionEntity.isDeleted.isFalse()))
            .fetchOne();
    }

    //
    @Override
    public List<CommentsInfo> getCommentsListByAuctionId(String auctionId) {
        return jpaQueryFactory.select(
                Projections.constructor(
                    CommentsInfo.class,
                    commentsEntity.writer,
                    commentsEntity.contents,
                    commentsEntity.createDateTime
                )
            ).from(commentsEntity)
            .where(commentsEntity.auction.id.eq(auctionId)
                .and(commentsEntity.isDeleted.isFalse()))
            .fetch();
    }

    @Override
    public void updateIsDeletedById(String id, boolean state) {
        jpaQueryFactory.update(auctionEntity)
            .set(auctionEntity.isDeleted, state)
            .where(auctionEntity.id.eq(id))
            .execute();
    }

    @Override
    public Page<AuctionsRes> getAuctionListByStateIsWaiting(Pageable pageable, AuctionsReq auctionsReq) {
        List<AuctionsRes> fetch = jpaQueryFactory
            .select(
                Projections.constructor(
                    AuctionsRes.class,
                    auctionEntity.id.as("auctionId"),
                    memberEntity.name.as("sellerName"),
                    auctionEntity.title,
                    auctionEntity.openDateTime,
                    auctionEntity.closedDateTime,
                    auctionEntity.createDateTime,
                    auctionEntity.minimumBidAmount
                )
            ).from(auctionEntity)
            .leftJoin(memberEntity).on(auctionEntity.sellerId.eq(memberEntity.id))
            .where(auctionEntity.state.eq(AuctionState.WAITING)
                .and(auctionEntity.isDeleted.isFalse())
                .and(minOpenDateGoe(auctionsReq.getMinOpenDate()))
                .and(maxOpenDateLt(auctionsReq.getMaxOpenDate()))
                .and(minBidAmountGoe(auctionsReq.getMinBidAmount()))
                .and(maxBidAmountLoe(auctionsReq.getMaxBidAmount()))
                .and(containTitleKeyword(auctionsReq.getTitleKeyword()))
                .and(sellerKeywordEq(auctionsReq.getSellerKeyword()))
                .and(searchTitleAndSellerKeyword(auctionsReq.getTitleAndSellerKeyword()))
            )
            .orderBy(getOrderSpecifier(pageable.getSort()).toArray(OrderSpecifier[]::new))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long count = jpaQueryFactory.select(auctionEntity.count())
            .from(auctionEntity)
            .leftJoin(memberEntity).on(auctionEntity.sellerId.eq(memberEntity.id))
            .where(auctionEntity.state.eq(AuctionState.WAITING)
                .and(auctionEntity.isDeleted.isFalse())
                .and(minOpenDateGoe(auctionsReq.getMinOpenDate()))
                .and(maxOpenDateLt(auctionsReq.getMaxOpenDate()))
                .and(minBidAmountGoe(auctionsReq.getMinBidAmount()))
                .and(maxBidAmountLoe(auctionsReq.getMaxBidAmount()))
                .and(containTitleKeyword(auctionsReq.getTitleKeyword()))
                .and(sellerKeywordEq(auctionsReq.getSellerKeyword()))
                .and(searchTitleAndSellerKeyword(auctionsReq.getTitleAndSellerKeyword()))
            )
            .fetchFirst();

        return PageableExecutionUtils.getPage(fetch, pageable, () -> count);
    }


    // Pageable에서 다중 sort를 동적으로 구현하기 위한 메서드
    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        PathBuilder<?> orderByExpression = new PathBuilder(QAuctionEntity.class, "auctionEntity");
        if (sort.isEmpty()) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, orderByExpression.get("createDateTime")));
        } else {
            sort.stream().forEach(order -> {
                Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                String prop = order.getProperty();
                orderSpecifiers.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
            });
        }
        return orderSpecifiers;
    }

    private BooleanExpression minOpenDateGoe(LocalDate minOpenDate){
        return minOpenDate != null ? auctionEntity.openDateTime.goe(minOpenDate.atTime(LocalTime.MIN)) : null;
    }

    private BooleanExpression maxOpenDateLt(LocalDate maxOpenDate){
        return maxOpenDate != null ? auctionEntity.openDateTime.lt(maxOpenDate.plusDays(1).atTime(LocalTime.MIN)) : null;
    }

    private BooleanExpression minBidAmountGoe(Integer minBidAmount){
        return minBidAmount != null ? auctionEntity.minimumBidAmount.goe(minBidAmount) : null;
    }

    private BooleanExpression maxBidAmountLoe(Integer maxBidAmount){
        return maxBidAmount != null ? auctionEntity.minimumBidAmount.loe(maxBidAmount) : null;
    }

    private BooleanExpression containTitleKeyword(String titleKeyword){
        if(StringUtils.isNullOrEmpty(titleKeyword)) {
            return null;
        }
        BooleanExpression result = Expressions.asBoolean(true).isFalse();
        String[] splitKeyword = titleKeyword.trim().split(" ");
        for (String keyword : splitKeyword) {
            result = result.or(auctionEntity.title.contains(keyword));
        }
        return result;
    }

    private BooleanExpression sellerKeywordEq(String sellerKeyword){
        if(StringUtils.isNullOrEmpty(sellerKeyword)) {
            return null;
        }
        return memberEntity.name.eq(sellerKeyword);
    }

    private BooleanExpression searchTitleAndSellerKeyword(String titleAndSellerKeyword){
        if(StringUtils.isNullOrEmpty(titleAndSellerKeyword) ) {
            return null;
        }
        BooleanExpression result = Expressions.asBoolean(true).isFalse();
        return result.or(containTitleKeyword(titleAndSellerKeyword)).or(sellerKeywordEq(titleAndSellerKeyword));
    }
}
