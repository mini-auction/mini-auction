package com.mini.auction.auction.application.port.out;

public interface AuctionNullCheck {
    void existsByIdAndIsDeletedFalse(String id);

}
