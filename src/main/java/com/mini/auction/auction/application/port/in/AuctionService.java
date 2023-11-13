package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface AuctionService {

    void createAuction(AuctionReq.CreateAuction req);

    void updateAuction(String id, AuctionReq req);

    AuctionRes getAuctionDetail(String id);

    void removeAuction(String id);


    Page<AuctionsRes> getWaitingAuctionsPage(Pageable pageable);

}
