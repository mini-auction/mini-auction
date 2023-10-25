package com.mini.auction.auction.domain;

import com.mini.auction.common.BaseEntity;
import com.mini.auction.common.enums.TermsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Terms extends BaseEntity {
    private String contents;
    private TermsType type;

    Terms(
        String id,
        boolean isDeleted,
        LocalDateTime createDateTime,
        LocalDateTime updateDateTime,
        String contents,
        TermsType type
    ){
        this.setField(
            isDeleted,
            id
        );

        this.contents = contents;
        this.type = type;
    }
}

