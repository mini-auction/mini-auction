package com.mini.auction.user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.mini.auction.user.application.port.in.UserSignService;

@RestController
@RequiredArgsConstructor //기본 생성자를 자동으로 만들어줌.
class UserRegistrationController {

    private final UserSignService userSignService;

}
