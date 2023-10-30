package com.mini.auction.auction.domain;

import com.mini.auction.common.domian.BaseEntity;
import com.mini.auction.common.enums.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Auction extends BaseEntity {

    private String sellerId;

    private AuctionDetail detail;

    private AuctionState state = AuctionState.WAITING;

    public Auction setBaseEntity(boolean isDeleted, String id){
        this.id = id;
        this.isDeleted = isDeleted;
        return this;
    }

}