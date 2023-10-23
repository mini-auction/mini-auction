package com.mini.auction.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.mini.auction.member.application.port.in.MemberSignService;

@RestController
@RequiredArgsConstructor //기본 생성자를 자동으로 만들어줌.
class MemberRegistrationController {

    private final MemberSignService memberSignService;

}
