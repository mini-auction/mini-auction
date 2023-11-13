package com.mini.auction.auction.adapter.in.web.dto;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AuctionsReq {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minOpenDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxOpenDate;

    @Max(1000000000)
    private Integer minBidAmount;

    @Max(1000000000)
    private Integer maxBidAmount;

    @Length(max = 20)
    private String titleKeyword;

    @Length(max = 20)
    private String sellerKeyword;

    @Length(max = 20)
    private String titleAndSellerKeyword;


}
