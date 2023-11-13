package com.mini.auction.auction.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AuctionsReq {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minOpenDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxOpenDate;


}
