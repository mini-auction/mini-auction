package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.common.exceptionHandler.customException.NotFoundAuthException;
import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.out.FindMemberPort;
import com.mini.auction.member.application.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

/*
    패키지 외부에서 Entity Repository 에 직접 의존하지 않도록 해주는 역할을 한다.
 */
@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements MemberPort, FindMemberPort {
    private final MemberRepository memberRepository;
    private final MemberEntityMapper memberEntityMapper;

    @Override
    public boolean existById(String id) {
        return memberRepository.existsById(id);
    }

    @Override
    public void save(RegistrationInfoReq req) {
        MemberEntity register = memberEntityMapper.mapToEntity(memberEntityMapper.mapToMember(req));
        try {
            memberRepository.save(register);
        } catch (Exception e) {
            throw new BadRequestException(new CustomResponse(ExceptionCode.E00003));
        }
    }

    @Override
    public JwtToken getMemberByEmailAndPassword(LoginReq req) {
        if (memberRepository.getMemberByEmailAndPassword(req) == null) {
            throw new NotFoundAuthException(new CustomResponse(ExceptionCode.E10001));
        } else {
            return memberRepository.getMemberByEmailAndPassword(req);
        }
    }
}
