package com.mini.auction.auction.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class CommentsInfo {
    private String writer;
    private String contents;
    private LocalDateTime createDateTime;
}
