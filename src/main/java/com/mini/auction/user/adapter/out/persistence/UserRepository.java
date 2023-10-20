package com.mini.auction.user.adapter.out.persistence;

import com.mini.auction.user.adapter.out.persistence.infrastructure.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, String>, UserCustomRepository {
}
