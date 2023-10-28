package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.adapter.in.web.dto.AuctionDetailRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.domain.Auction;

import java.util.List;

public interface AuctionPort {
    String save(AuctionReq.CreateAuction req);

    boolean existsByIdAndIsDeletedFalse(String id);

    void updateDetailById(String id, AuctionReq req);

    Auction findById(String id);

    AuctionDetailRes getAuctionDetailById(String id);

    List<CommentsInfo> getCommentsListByAuctionId(String auctionId);

    void updateIsDeletedById(String id, boolean state);


}
