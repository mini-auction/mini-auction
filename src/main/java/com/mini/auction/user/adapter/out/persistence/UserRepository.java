package com.mini.auction.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, String> {
}
