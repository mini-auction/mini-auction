package com.mini.auction.config.jwt;

import com.mini.auction.common.exceptionHandler.customException.JwtException;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JwtService {
    // TODO: Object -> 회원 객체로 변경해야함
    <T> String create(Object memberInfo);
    Map<String, Object> getClaims(String key) throws JwtException;
    boolean isUsable(String token) throws JwtException;
}
