package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.adapter.out.persistence.QAuctionEntity;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.enums.AuctionState;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

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
                    // TODO: subquery와 join 성능 비교
                    ExpressionUtils.as(
                        JPAExpressions.select(memberEntity.name)
                            .from(memberEntity)
                            .where(memberEntity.id.eq(auctionEntity.sellerId)),
                        "sellerName"
                    ),
                    auctionEntity.title,
                    auctionEntity.openDateTime,
                    auctionEntity.closedDateTime,
                    auctionEntity.createDateTime,
                    auctionEntity.minimumBidAmount
                )
            ).from(auctionEntity)
            .where(auctionEntity.state.eq(AuctionState.WAITING).and(auctionEntity.isDeleted.isFalse())
                .and(auctionEntity.openDateTime.goe(auctionsReq.getMinOpenDate().atStartOfDay()))
                .and(auctionEntity.openDateTime.loe(auctionsReq.getMaxOpenDate().atTime(LocalTime.MAX))))
            .orderBy(getOrderSpecifier(pageable.getSort()).toArray(OrderSpecifier[]::new))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long count = jpaQueryFactory.select(auctionEntity.count())
            .from(auctionEntity)
            .where(auctionEntity.state.eq(AuctionState.WAITING).and(auctionEntity.isDeleted.isFalse())
                .and(auctionEntity.openDateTime.goe(auctionsReq.getMinOpenDate().atStartOfDay()))
                .and(auctionEntity.openDateTime.loe(auctionsReq.getMaxOpenDate().atTime(LocalTime.MAX))))
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
}
