package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.adapter.in.web.dto.*;
import com.mini.auction.auction.application.port.in.AuctionService;
import com.mini.auction.auction.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionPort {
    String save(AuctionReq.CreateAuction req);

    boolean existsByIdAndIsDeletedFalse(String id);

    void updateDetailById(String id, AuctionReq req);

    Auction findById(String id);

    AuctionRes getAuctionDetailById(String id);

    List<CommentsInfo> getCommentsListByAuctionId(String auctionId);

    void updateIsDeletedById(String id, boolean state);

    Page<AuctionsRes> getAuctionListByStateIsWaiting(Pageable pageable, AuctionsReq auctionsReq, AuctionService.Param dateTimeParam);

}
