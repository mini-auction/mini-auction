package com.mini.auction.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service("JwtService")
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private static final String SALT = "뭐얼마나길어야하노이노마";

    private final SecretKey secretKey = Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8));

    private final long ACCESS_TOKEN_VALIDATiON_SECOND = 60 * 30;


    @Override
    public <T> String create(Object memberInfo){

        return Jwts.builder()
                .signWith(secretKey)
                .setSubject("member")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDATiON_SECOND * 1000L))
                .claim("memberInfo", memberInfo)
                .compact();
    }

    @Override
    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.get(key);
        return value;

    }

    @Override
    public boolean isUsable(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException |
                UnsupportedJwtException |
                MalformedJwtException  |
                SignatureException |
                IllegalArgumentException e){
            e.printStackTrace();
            throw new InvalidParameterException("유효하지 않은 토큰입니다");
        }
    }



}
