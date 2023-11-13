package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsReq;
import com.mini.auction.auction.adapter.in.web.dto.CommentsInfo;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.domain.AuctionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionCustomRepository {

    void updateDetail(String id, AuctionDetail detail);

    AuctionRes getAuctionDetailById(String id);

    List<CommentsInfo> getCommentsListByAuctionId(String auctionId);

    void updateIsDeletedById(String id, boolean state);

    Page<AuctionsRes> getAuctionListByStateIsWaiting(Pageable pageable, AuctionsReq auctionsReq);
}
