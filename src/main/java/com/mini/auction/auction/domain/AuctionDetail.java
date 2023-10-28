package com.mini.auction.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDetail {

    private String title;

    private String contents;

    private LocalDateTime openDateTime;

    private LocalDateTime closedDateTime;

    private int minimumBidAmount = 0;
}
