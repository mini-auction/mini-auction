package com.mini.auction.auction.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionTermsMappingLogRepository extends
    JpaRepository<AuctionTermsMappingLogEntity, AuctionTermsMappingLogEntity.AuctionTermsMappingLogId> {
}
