package com.mini.auction.auction.domain;

import com.mini.auction.common.domian.BaseEntity;
import com.mini.auction.common.enums.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class Auction extends BaseEntity {

    private String sellerId;

    private AuctionDetail detail;

    private AuctionState state;

    private List<Comments> comments;

    public Auction(
        String sellerId,
        AuctionDetail detail,
        AuctionState state,
        List<Comments> comments,
        String id,
        boolean isDeleted
    ){
        super(isDeleted, id);
        this.sellerId = sellerId;
        this.detail = detail;
        this.state = state;
        this.comments = comments;
    }

}