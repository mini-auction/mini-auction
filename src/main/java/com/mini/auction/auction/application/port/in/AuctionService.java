package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuctionService {

    void createAuction(AuctionReq.CreateAuction req);

    @Transactional
    void updateAuction(String id, AuctionReq req);

    Page<AuctionsRes> getWaitingAuctionsPage(Pageable pageable);

    List<AuctionsRes> getWaitingAuctionsList(Pageable pageable);
}
