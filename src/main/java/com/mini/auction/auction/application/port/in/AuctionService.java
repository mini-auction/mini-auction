package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;

public interface AuctionService {

    void createAuction(CreateAuction request);


}
