package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.application.port.out.AuctionTermsMappingLogPort;
import com.mini.auction.auction.domain.AuctionTermsMappingLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class AuctionTermsMappingLogPersistenceAdapter implements AuctionTermsMappingLogPort {

    private final AuctionTermsMappingLogRepository auctionTermsMappingLogRepository;

    @Override
    public void save(String auctionId, String termsId) {
        auctionTermsMappingLogRepository.save(
            new AuctionTermsMappingLogEntity(
                auctionId,
                termsId
            )
        );
    }
}
