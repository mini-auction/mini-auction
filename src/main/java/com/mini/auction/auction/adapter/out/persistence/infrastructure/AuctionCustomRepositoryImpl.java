package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionDetailRes;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.adapter.out.persistence.QAuctionEntity;
import com.mini.auction.auction.domain.AuctionDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.mini.auction.auction.adapter.out.persistence.QAuctionEntity.auctionEntity;
import static com.mini.auction.auction.adapter.out.persistence.QCommentsEntity.commentsEntity;

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
    public AuctionDetailRes getAuctionDetailById(String id) {
        return jpaQueryFactory.select(
            Projections.constructor(
                AuctionDetailRes.class,
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

    @Override
    public List<CommentsInfo> getCommentsListByAuctionId(String auctionId){
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
}
