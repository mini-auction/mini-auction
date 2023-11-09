package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.member.adapter.out.persistence.infrastructure.MemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<MemberEntity, String>, MemberCustomRepository {

    boolean existsByEmail(String email);
}
