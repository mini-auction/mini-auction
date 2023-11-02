package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.domain.Terms;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.mini.auction.auction.adapter.out.persistence.QTermsEntity.termsEntity;

@RequiredArgsConstructor
class TermsCustomRepositoryImpl implements TermsCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Terms getTerms(String id) {
        return jpaQueryFactory.select(
            Projections.constructor(
                Terms.class,
                termsEntity.id,
                termsEntity.isDeleted,
                termsEntity.contents,
                termsEntity.type,
                termsEntity.createDateTime,
                termsEntity.updateDateTime
            )
        ).from(termsEntity)
            .where(termsEntity.id.eq(id))
            .fetchOne();
    }
}
