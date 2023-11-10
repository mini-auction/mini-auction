package com.mini.auction.auction.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.auction.common.enums.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuctionsRes {

    private String auctionId;
    private String sellerName;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedDateTime;

}
