package com.mini.auction.auction.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionReq {
    @NotBlank
    private String sellerId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openDateTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedDateTime;

    @NotNull
    private Integer minimumBidAmount;

    @Getter
    public static class CreateAuction extends AuctionReq {

        @NotNull
        private Boolean isAgree;

        @NotBlank
        private String termsId;

    }
}
