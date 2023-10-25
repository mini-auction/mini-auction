package com.mini.auction.auction.adapter.in.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAuction {
    private String sellerId;
    private String title;
    private String contents;
    private LocalDateTime openDateTime;
    private LocalDateTime closedDateTime;
    private int minimumBidAmount = 0;
    private boolean isAgree;

}
