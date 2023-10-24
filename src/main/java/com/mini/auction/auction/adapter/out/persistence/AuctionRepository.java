package com.mini.auction.auction.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuctionRepository extends JpaRepository<AuctionEntity, String> {
}
