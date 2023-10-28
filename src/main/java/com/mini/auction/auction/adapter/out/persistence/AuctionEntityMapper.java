package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
class AuctionEntityMapper {

    AuctionEntity mapToEntity(Auction auction){
        return new AuctionEntity(
            auction.getSellerId(),
            auction.getDetail().getTitle(),
            auction.getDetail().getContents(),
            auction.getDetail().getOpenDateTime(),
            auction.getDetail().getClosedDateTime(),
            auction.getDetail().getMinimumBidAmount(),
            auction.getState(),
            Collections.emptyList()
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
                createAuction.getMinimumBidAmount()
            ),
            AuctionState.WAITING,
            Collections.emptyList()
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
                    auctionEntity.getMinimumBidAmount()
                )
            )
            .state(auctionEntity.getState())
            .comments(Collections.emptyList())
            .build()
            .setBaseEntity(auctionEntity.isDeleted(), auctionEntity.getId());
    }

    AuctionDetail mapToAuctionDetail(
        String title,
        String contents,
        LocalDateTime openDateTime,
        LocalDateTime closedDateTime,
        int minimumBidAmount
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
