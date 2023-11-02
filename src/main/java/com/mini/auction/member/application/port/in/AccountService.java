package com.mini.auction.member.application.port.in;

import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountService {
    void createMember(RegistrationInfoReq req);

    void login(LoginReq req, HttpServletResponse res);
}
