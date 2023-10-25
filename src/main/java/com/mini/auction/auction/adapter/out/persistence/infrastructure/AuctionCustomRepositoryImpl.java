package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class AuctionCustomRepositoryImpl implements AuctionCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

}
