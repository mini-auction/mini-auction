package com.mini.auction.member.application.service;

import com.mini.auction.config.jwt.JwtService;
import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.in.AccountService;
import com.mini.auction.member.application.port.out.AccountPort;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountPort accountPort;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void createMember(RegistrationInfoReq req) {
        accountPort.save(req);
    }

    @Override
    public void login(LoginReq req, HttpServletResponse res) {
        // 이메일 존인 여부 확인
        accountPort.existByEmail(req.getEmail());
        // 이메일, 비밀번호 일치 확인
        JwtToken memberInfo = accountPort.getMemberByEmailAndPassword(req);
        // jwt 토큰 생성
        String token = jwtService.create(memberInfo);
        logger.info("토큰 내용: {}", token);
        // http 헤더에 토큰 setting
        res.setHeader(HttpHeaders.AUTHORIZATION, token);
    }
}
