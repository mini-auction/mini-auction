package com.mini.auction.auction.application.port.out;

import com.mini.auction.auction.domain.Auction;

public interface AuctionPort {
    void save(Auction auction);
}
