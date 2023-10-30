package com.mini.auction.member.application.port.in;

import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;

public interface AccountService {
    void createMember(RegistrationInfoReq req);
}
