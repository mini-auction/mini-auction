package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.auction.domain.Money;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class AuctionEntityMapper {

    AuctionEntity mapToEntity(Auction auction){
        return new AuctionEntity(
            auction.getSellerId(),
            auction.getDetail().getTitle(),
            auction.getDetail().getContents(),
            auction.getDetail().getOpenDateTime(),
            auction.getDetail().getClosedDateTime(),
            auction.getDetail().getMinimumBidAmount().getAmount(),
            auction.getState()
        );
    }

    Auction mapToDomain(AuctionReq.CreateAuction createAuction){
        return new Auction(
            createAuction.getSellerId(),
            mapToAuctionDetail(
                createAuction.getTitle(),
                createAuction.getContents(),
                createAuction.getOpenDateTime(),
                createAuction.getClosedDateTime(),
                new Money(createAuction.getMinimumBidAmount())
            ),
            AuctionState.WAITING
        );
    }

    Auction mapToDomain(AuctionEntity auctionEntity) {
        return Auction.builder()
            .sellerId(auctionEntity.getSellerId())
            .detail(
                mapToAuctionDetail(
                    auctionEntity.getTitle(),
                    auctionEntity.getContents(),
                    auctionEntity.getOpenDateTime(),
                    auctionEntity.getClosedDateTime(),
                    new Money(auctionEntity.getMinimumBidAmount())
                )
            )
            .state(auctionEntity.getState())
            .build()
            .setBaseEntity(auctionEntity.isDeleted(), auctionEntity.getId());
    }

    AuctionDetail mapToAuctionDetail(
        String title,
        String contents,
        LocalDateTime openDateTime,
        LocalDateTime closedDateTime,
        Money minimumBidAmount
    ){
        return new AuctionDetail(
            title,
            contents,
            openDateTime,
            closedDateTime,
            minimumBidAmount
        );
    }


}
