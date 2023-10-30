package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import org.springframework.transaction.annotation.Transactional;

public interface AuctionService {

    void createAuction(AuctionReq.CreateAuction req);

    void updateAuction(String id, AuctionReq req);

}
