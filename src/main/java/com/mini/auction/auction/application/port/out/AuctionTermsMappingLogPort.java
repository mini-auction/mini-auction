package com.mini.auction.auction.application.port.out;


public interface AuctionTermsMappingLogPort {
    void save(String auctionId, String termsId);
}
