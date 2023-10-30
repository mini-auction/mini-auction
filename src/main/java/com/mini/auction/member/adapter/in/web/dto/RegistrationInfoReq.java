package com.mini.auction.member.adapter.in.web.dto;

import com.mini.auction.common.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfoReq {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Password
    private String password;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 13)
    private String phoneNo;
}
