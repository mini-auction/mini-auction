package com.mini.auction.auction.domain;

import com.mini.auction.common.domian.BaseEntity;
import com.mini.auction.common.enums.AuctionState;

import java.time.LocalDateTime;

public class Auction extends BaseEntity {

    private String sellerId;

    private String title;

    private String contents;

    private LocalDateTime openDateTime;

    private LocalDateTime closedDateTime;

    private final int minimumBidAmount = 0;

    private final AuctionState state = AuctionState.WAITING;

}