package com.mini.auction.auction.domain;


import com.mini.auction.common.domian.BaseDomainEntity;
import lombok.Getter;

@Getter
public class Comments extends BaseDomainEntity {
    private String contents;

    private String writerId;

    public Comments(
        String contents,
        String memberId,
        String id,
        boolean isDeleted
    ){
        super(isDeleted, id);
        this.contents = contents;
        this.writerId = memberId;
    }
}
