package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionCustomRepository {

    void updateDetail(String id, AuctionDetail detail);

    Page<AuctionsRes> findAllByStateIsWaitingPage(AuctionState waiting, Pageable pageable);
}
