package com.mini.auction.auction.application.port.in;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AuctionService {

    record Param(LocalDateTime minOpenDateTime, LocalDateTime maxOpenDatetime){
    }

    void createAuction(AuctionReq.CreateAuction req);

    void updateAuction(String id, AuctionReq req);

    AuctionRes getAuctionDetail(String id);

    void removeAuction(String id);

    Page<AuctionsRes> getWaitingAuctionsPage(Pageable pageable, AuctionsReq auctionsReq);

}
