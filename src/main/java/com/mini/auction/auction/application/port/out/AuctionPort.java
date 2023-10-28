package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.domain.Auction;

public interface AuctionPort {
    String save(AuctionReq.CreateAuction req);

    boolean existById(String id);

    void updateById(String id, AuctionReq req);

    Auction findById(String id);


}
