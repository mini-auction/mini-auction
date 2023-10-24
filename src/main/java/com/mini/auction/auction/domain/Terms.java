package com.mini.auction.auction.domain;

import com.mini.auction.common.BaseEntity;
import com.mini.auction.common.enums.TermsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Terms extends BaseEntity {
    private String contents;
    private TermsType type;

    public Terms(
        String
    ){

    }
}
