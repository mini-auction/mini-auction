package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.out.persistence.infrastructure.TermsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface TermsRepository extends JpaRepository<TermsEntity, String>, TermsCustomRepository {
}
