package com.mini.auction.config.jwt;

import java.util.Map;

public interface JwtService {
    // TODO: Object -> 회원 객체로 변경해야함
    <T> String create(Object memberInfo);
    Map<String, Object> getClaims(String key);
    boolean isUsable(String token);
}
