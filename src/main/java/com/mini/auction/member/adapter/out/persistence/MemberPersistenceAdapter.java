package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.member.application.port.out.FindMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
    패키지 외부에서 Entity Repository 에 직접 의존하지 않도록 해주는 역할을 한다.
 */
@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements
    FindMemberPort {
    private final MemberRepository memberRepository;

    @Override
    public boolean existById(String id) {
        return memberRepository.existsById(id);
    }
}
