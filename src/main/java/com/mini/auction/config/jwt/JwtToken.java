package com.mini.auction.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtToken {
    private String name;

    private String email;
}
