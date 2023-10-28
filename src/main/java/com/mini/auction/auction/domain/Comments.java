package com.mini.auction.auction.domain;


import com.mini.auction.common.domian.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Comments extends BaseEntity {
    private String contents;

    private String memberId;
}
