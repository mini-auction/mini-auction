package com.mini.auction.member.application.port.out;

import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;

public interface AccountPort {
    void save(RegistrationInfoReq req);

    JwtToken getMemberByEmailAndPassword(LoginReq req);

    void existByEmail(String email);
}
