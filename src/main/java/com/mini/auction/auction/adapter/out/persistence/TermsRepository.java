package com.mini.auction.auction.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface TermsRepository extends JpaRepository<TermsEntity, String> {
}
