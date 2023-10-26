package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.auction.domain.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AuctionPersistenceAdapter implements AuctionPort {
    private final AuctionRepository auctionRepository;
    private final AuctionEntityMapper auctionEntityMapper;

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auctionEntityMapper.mapToEntity(auction));
    }


}
