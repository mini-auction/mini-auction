package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.domain.Auction;
import org.springframework.stereotype.Component;

@Component
class AuctionEntityMapper {

    AuctionEntity mapToEntity(Auction auction){
        AuctionEntity auctionEntity = new AuctionEntity(
            auction.getSellerId(),
            auction.getTitle(),
            auction.getContents(),
            auction.getOpenDateTime(),
            auction.getClosedDateTime(),
            auction.getMinimumBidAmount(),
            auction.getState()
        );
        auctionEntity.setField(auction.getId(), auction.isDeleted());
        return auctionEntity;
    }

}
