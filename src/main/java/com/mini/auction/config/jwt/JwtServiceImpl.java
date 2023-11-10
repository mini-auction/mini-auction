package com.mini.auction.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("JwtService")
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${JWT_TOKEN}")
    private String SALT;

    private SecretKey secretKey;

    private final long ACCESS_TOKEN_VALIDATiON_SECOND = 60 * 30;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8));
        System.out.println(secretKey);
    }

    @Override
    public <T> String create(JwtToken memberInfo) {
        return Jwts.builder()
            .signWith(secretKey)
            .setSubject("member")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDATiON_SECOND * 1000L))
            .claim("memberInfo", memberInfo)
            .compact();
    }

    @Override
    public Map<String, Object> getClaims(String key) throws JwtException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(jwt)
            .getBody();
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (HashMap<String, Object>) Objects.requireNonNull(claims).get(key);
        return value;

    }

    @Override
    public boolean isUsable(String token) throws JwtException {
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);

        return true;
    }

}
