package com.mini.auction.auction.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionPersistenceAdapter {
    private final AuctionRepository auctionRepository;
}
