package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.common.exceptionHandler.customException.NotFoundAuthException;
import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.out.AccountPort;
import com.mini.auction.member.application.port.out.FindMemberPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/*
    패키지 외부에서 Entity Repository 에 직접 의존하지 않도록 해주는 역할을 한다.
 */
@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements AccountPort, FindMemberPort {
    private final MemberRepository memberRepository;
    private final MemberEntityMapper memberEntityMapper;

    private final Logger logger = LoggerFactory.getLogger(MemberPersistenceAdapter.class);

    @Override
    public boolean existsByIdAndIsDeletedFalse(String id) {
        return memberRepository.existsByIdAndIsDeletedFalse(id);
    }

    @Override
    public void save(RegistrationInfoReq req) {
        MemberEntity register = memberEntityMapper.mapToEntity(memberEntityMapper.mapToMember(req));
        try {
            memberRepository.save(register);
        } catch (DataIntegrityViolationException e) { // sql 오류나 입력된 data가 잘못된 경우 동작하는 exception
            logger.error("error {}", req, e);
            throw new BadRequestException(new CustomResponse(ExceptionCode.E10002));
        }
    }

    @Override
    public JwtToken getMemberByEmailAndPassword(LoginReq req) {
        JwtToken token = memberRepository.getMemberByEmailAndPassword(req);
        if (token == null) {
            throw new NotFoundAuthException(new CustomResponse(ExceptionCode.E10003));
        } else {
            return token;
        }
    }

    @Override
    public void existByEmail(String email) {
        if(!memberRepository.existsByEmail(email)) {
            throw new NotFoundAuthException(new CustomResponse(ExceptionCode.E10001));
        }
    }
}
