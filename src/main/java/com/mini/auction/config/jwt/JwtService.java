package com.mini.auction.config.jwt;

import java.util.Map;

public interface JwtService {
    <T> String create(JwtToken memberInfo);
    Map<String, Object> getClaims(String key);
    boolean isUsable(String token);
}
