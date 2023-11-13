package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.util.List;

public interface AuctionPort {
    String save(AuctionReq.CreateAuction req);

    boolean existsByIdAndIsDeletedFalse(String id);

    void updateDetailById(String id, AuctionReq req);

    Auction findById(String id);

    AuctionRes getAuctionDetailById(String id);

    List<CommentsInfo> getCommentsListByAuctionId(String auctionId);

    void updateIsDeletedById(String id, boolean state);

    Page<AuctionsRes> getAuctionListByStateIsWaiting(Pageable pageable);

}
