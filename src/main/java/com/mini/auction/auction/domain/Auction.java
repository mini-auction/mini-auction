package com.mini.auction.auction.domain;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
import com.mini.auction.common.domian.BaseEntity;
import com.mini.auction.common.enums.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Auction extends BaseEntity {

    private String sellerId;

    private String title;

    private String contents;

    private LocalDateTime openDateTime;

    private LocalDateTime closedDateTime;

    private int minimumBidAmount = 0;

    private final AuctionState state = AuctionState.WAITING;

    public static Auction createAuction(CreateAuction createAuction){
        return new Auction(
            createAuction.getSellerId(),
            createAuction.getTitle(),
            createAuction.getContents(),
            createAuction.getOpenDateTime(),
            createAuction.getClosedDateTime(),
            createAuction.getMinimumBidAmount()
        );
    }

}