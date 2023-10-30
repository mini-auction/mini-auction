package com.mini.auction.auction.domain;


import com.mini.auction.common.domian.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Comments extends BaseEntity {
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
