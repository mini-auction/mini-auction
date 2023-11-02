package com.mini.auction.config.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtToken {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
