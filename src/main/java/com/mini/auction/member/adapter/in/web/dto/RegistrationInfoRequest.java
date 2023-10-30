package com.mini.auction.member.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfoRequest {
    private String name;
    private String password;
    private String email;
    private String phoneNo;
}
