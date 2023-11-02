package com.mini.auction.member.application.service;

import com.mini.auction.config.jwt.JwtService;
import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.in.AccountService;
import com.mini.auction.member.application.port.out.MemberPort;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final MemberPort memberPort;
    private final JwtService jwtService;

    @Override
    public void createMember(RegistrationInfoReq req) {
        memberPort.save(req);
    }

    @Override
    public void login(LoginReq req, HttpServletResponse res) {
        JwtToken memberInfo = memberPort.getMemberByEmailAndPassword(req);
        String token = jwtService.create(memberInfo);
        logger.info("토큰 내용: {}", token);
        res.setHeader(HttpHeaders.AUTHORIZATION, token);
    }
}
