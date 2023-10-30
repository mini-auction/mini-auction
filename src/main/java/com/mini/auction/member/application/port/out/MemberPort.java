package com.mini.auction.member.application.port.out;

import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;

public interface MemberPort {
    void save(RegistrationInfoReq req);
}
