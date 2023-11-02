package com.mini.auction.member.adapter.out.persistence.infrastructure;

import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.Projections;

import static com.mini.auction.member.adapter.out.persistence.QMemberEntity.memberEntity;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public JwtToken getMemberByEmailAndPassword(LoginReq req) {
        return jpaQueryFactory.select(
                Projections.constructor(
                        JwtToken.class,
                        memberEntity.name,
                        memberEntity.email
                )
        ).from(memberEntity)
        .where(memberEntity.email.eq(req.getEmail())
            .and(memberEntity.password.eq(req.getPassword()))
        ).fetchOne();
    }
}
