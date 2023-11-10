package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionPort {
    String save(AuctionReq.CreateAuction req);

    boolean existById(String id);

    void updateDetailById(String id, AuctionReq req);

    Auction findById(String id);

    Page<AuctionsRes> findAllByStateIsWaitingPage(AuctionState WAITING, Pageable pageable);

}
