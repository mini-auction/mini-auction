package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.out.MemberPort;
import com.mini.auction.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
    패키지 외부에서 Entity Repository 에 직접 의존하지 않도록 해주는 역할을 한다.
 */
@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements MemberPort {
    private final MemberRepository memberRepository;
    private final MemberEntityMapper memberEntityMapper;

    @Override
    public void save(RegistrationInfoReq req) {
        MemberEntity register = memberEntityMapper.mapToEntity(memberEntityMapper.mapToMember(req));
        try {
            memberRepository.save(register);
        }catch (Exception e){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E00003, e.getMessage()));
        }
    }
}
