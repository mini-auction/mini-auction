package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.domain.AuctionDetail;

import java.util.List;

public interface AuctionCustomRepository {

    void updateDetail(String id, AuctionDetail detail);

    AuctionRes getAuctionDetailById(String id);

    List<CommentsInfo> getCommentsListByAuctionId(String auctionId);

    void updateIsDeletedById(String id, boolean state);

}
