package com.mini.auction.auction.domain;

import com.mini.auction.common.domian.BaseDomainEntity;
import com.mini.auction.common.enums.TermsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Terms extends BaseDomainEntity {
    private String contents;
    private TermsType type;
}

