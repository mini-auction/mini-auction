package com.mini.auction.member.application.port.out;

import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.domain.Member;

import java.util.Optional;

public interface MemberPort {
    void save(RegistrationInfoReq req);

    JwtToken getMemberByEmailAndPassword(LoginReq req);
}
