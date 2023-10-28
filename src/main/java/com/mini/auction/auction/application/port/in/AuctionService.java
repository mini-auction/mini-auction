package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.AuctionDetailRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;

public interface AuctionService {

    void createAuction(AuctionReq.CreateAuction req);

    void updateAuction(String id, AuctionReq req);

    AuctionDetailRes getAuctionDetail(String id);

    void removeAuction(String id);


}
