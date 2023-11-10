package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.adapter.out.persistence.QAuctionEntity;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.enums.AuctionState;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mini.auction.auction.adapter.out.persistence.QAuctionEntity.auctionEntity;
import static com.mini.auction.member.adapter.out.persistence.QMemberEntity.memberEntity;

@RequiredArgsConstructor
class AuctionCustomRepositoryImpl implements AuctionCustomRepository{
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
    public List<AuctionsRes> findAllByStateIsWaitingList(AuctionState WAITING, Pageable pageable) {
         return jpaQueryFactory.select(
                Projections.constructor(
                        AuctionsRes.class,
                        auctionEntity.id.as("auctionId"),
                        ExpressionUtils.as(
                                JPAExpressions.select(memberEntity.name)
                                        .from(memberEntity)
                                        .where(memberEntity.id.eq(auctionEntity.sellerId)),
                                "sellerName"
                        ),
                        auctionEntity.title,
                        auctionEntity.openDateTime,
                        auctionEntity.closedDateTime
                )
        ).from(auctionEntity)
                .where(auctionEntity.state.eq(WAITING))
                .orderBy(getOrderSpecifier(pageable.getSort()).toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<AuctionsRes> findAllByStateIsWaitingPage(AuctionState WAITING, Pageable pageable) {
        List<AuctionsRes> fetch = jpaQueryFactory.select(
                        Projections.constructor(
                                AuctionsRes.class,
                                auctionEntity.id.as("auctionId"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(memberEntity.name)
                                                .from(memberEntity)
                                                .where(memberEntity.id.eq(auctionEntity.sellerId)),
                                        "sellerName"
                                ),
                                auctionEntity.title,
                                auctionEntity.openDateTime,
                                auctionEntity.closedDateTime
                        )
                ).from(auctionEntity)
                .where(auctionEntity.state.eq(WAITING))
                .orderBy(getOrderSpecifier(pageable.getSort()).toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var count = jpaQueryFactory.select(auctionEntity.count())
                .from(auctionEntity)
                .where(auctionEntity.state.eq(WAITING))
                .fetchOne();

        return PageableExecutionUtils.getPage(fetch,pageable, () -> count==null? 0:count);
    }


    // Pageable에서 다중 sort를 동적으로 구현하기 위한 메서드
    private List<OrderSpecifier> getOrderSpecifier(Sort sort){
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder<?> orderByExpression = new PathBuilder(QAuctionEntity.class, "auctionEntity");
            orderSpecifiers.add(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });
        return orderSpecifiers;
    }
}
