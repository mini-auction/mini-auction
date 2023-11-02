package com.mini.auction.config.jwt;

import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
public class JwtServiceImpl implements JwtService{

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
    public Map<String, Object> getClaims(String key) throws JwtException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            jwtExceptionHandler(e);
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> value = (HashMap<String, Object>) Objects.requireNonNull(claims).get(key);
        return value;

    }

    @Override
    public boolean isUsable(String token) throws JwtException {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            jwtExceptionHandler(e);
            return false;
        }
    }

    public void jwtExceptionHandler(Exception e) throws JwtException {
        if (e instanceof ExpiredJwtException exception){ // 토큰 만료
            ErrorResponse error = new ErrorResponse(ErrorCode.E00004, exception.getMessage());
            logger.error("ExpiredJwtException: ", exception);
            throw new com.mini.auction.common.exceptionHandler.customException.JwtException(error);
        } else if (e instanceof UnsupportedJwtException exception){ // 토큰 형태 인증 불가
            ErrorResponse error = new ErrorResponse(ErrorCode.E00005, exception.getMessage());
            logger.error("UnsupportedJwtException: ", exception);
            throw new com.mini.auction.common.exceptionHandler.customException.JwtException(error);
        } else if (e instanceof MalformedJwtException exception){ // 토큰 구조 불일치
            ErrorResponse error = new ErrorResponse(ErrorCode.E00005, exception.getMessage());
            logger.error("MalformedJwtException: ", exception);
            throw new com.mini.auction.common.exceptionHandler.customException.JwtException(error);
        } else if (e instanceof SignatureException exception){ // jwt 서명실패
            ErrorResponse error = new ErrorResponse(ErrorCode.E00005, exception.getMessage());
            logger.error("SignatureException: ", exception);
            throw new com.mini.auction.common.exceptionHandler.customException.JwtException(error);
        } else { // 그 외 모든 에러
            ErrorResponse error = new ErrorResponse(ErrorCode.E00005, e.getMessage());
            logger.error("UnauthorizedException: ", e);
            throw new JwtException(error);
        }
    }


}
