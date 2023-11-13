package com.mini.auction.auction.adapter.in.web.dto;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AuctionsReq {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSetter(nulls = Nulls.SKIP)
    private final LocalDate minOpenDate = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSetter(nulls = Nulls.SKIP)
    private final LocalDate maxOpenDate = LocalDate.now();

}
