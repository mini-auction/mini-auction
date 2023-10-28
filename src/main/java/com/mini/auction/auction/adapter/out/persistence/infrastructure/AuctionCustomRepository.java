package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.domain.AuctionDetail;

public interface AuctionCustomRepository {

    void updateDetail(String id, AuctionDetail detail);

}
