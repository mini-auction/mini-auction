package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.domain.AuctionDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.mini.auction.auction.adapter.out.persistence.QAuctionEntity.auctionEntity;

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
            .set(auctionEntity.minimumBidAmount, detail.getMinimumBidAmount())
            .set(auctionEntity.updateDateTime, LocalDateTime.now())
            .where(auctionEntity.id.eq(id))
            .execute();
    }
}
