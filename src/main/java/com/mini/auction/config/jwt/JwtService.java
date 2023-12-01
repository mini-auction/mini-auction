package com.mini.auction.config.jwt;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JwtService {
    <T> String create(JwtToken memberInfo);
    Map<String, Object> getClaims(String key);
    boolean isUsable(String token);
}
