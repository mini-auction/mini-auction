package com.mini.auction.member.adapter.in.web;

import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.application.port.in.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //기본 생성자를 자동으로 만들어줌.
@RequestMapping("/account")
class AccountController {

    private final AccountService accountService;

    @PostMapping("/registration")
    void registrationMember(@RequestBody @Valid RegistrationInfoReq req){
        accountService.createMember(req);
    }

}
