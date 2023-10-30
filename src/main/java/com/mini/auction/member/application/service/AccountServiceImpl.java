package com.mini.auction.member.application.service;

import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.in.AccountService;
import com.mini.auction.member.application.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class AccountServiceImpl implements AccountService {

    private final MemberPort memberPort;

    @Override
    public void createMember(RegistrationInfoReq req) {
        memberPort.save(req);
    }
}
