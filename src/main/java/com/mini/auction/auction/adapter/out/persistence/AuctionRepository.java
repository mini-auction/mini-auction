package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.out.persistence.infrastructure.AuctionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface AuctionRepository extends JpaRepository<AuctionEntity, String>, AuctionCustomRepository {

    boolean existsByIdAndIsDeletedFalse(String id);

}
