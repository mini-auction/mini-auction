package com.mini.auction.member.adapter.in.web.dto;

import com.mini.auction.common.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginReq {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Password
    private String password;
}
