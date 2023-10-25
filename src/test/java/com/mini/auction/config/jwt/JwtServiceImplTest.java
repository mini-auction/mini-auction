package com.mini.auction.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceImplTest {
    private final JwtService jwtService = new JwtServiceImpl();

    private static SecretKey secretKey;

    private record Member(String id, String name){
    }

    @Test
    void createJwtTest() {
        // when
        String jwt = jwtService.create(new Member("test_id", "test_name"));

        secretKey = Keys.hmacShaKeyFor("뭐얼마나길어야하노이노마".getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>) claims.get("memberInfo");

        // then
        assertEquals("test_id", value.get("id"));
    }

    @Test
    void isUsableSuccessCase() {
        // when
        secretKey = Keys.hmacShaKeyFor("뭐얼마나길어야하노이노마".getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .signWith(secretKey)
                .setSubject("member")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10))
                .claim("memberInfo", new Member("test_id", "test_name"))
                .compact();


        // then
        assertTrue(jwtService.isUsable(jwt));
    }

    // exception에 대한 검증 방법 찾아볼 수 있을 듯
//    @Test
//    void isUsableSignErrorCase() {
//        // when
//        secretKey = Keys.hmacShaKeyFor("이거는잘못된시그니처요".getBytes(StandardCharsets.UTF_8));
//        String jwt = Jwts.builder()
//                .signWith(secretKey)
//                .setSubject("user")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10))
//                .claim("user", "test_user")
//                .compact();
//
//        // then
//        assertThatThrownBy(() -> jwtService.isUsable(jwt))
//                .isInstanceOf(SignatureException.class);
//    }

    // exception에 대한 검증 방법 찾아볼 수 있을 듯
//    @Test
//    void isUsableExpireTimeErrorCase() throws InterruptedException {
//        // when
//        secretKey = Keys.hmacShaKeyFor("뭐얼마나길어야하노이노마".getBytes(StandardCharsets.UTF_8));
//        String jwt = Jwts.builder()
//                .signWith(secretKey)
//                .setSubject("user")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000))
//                .claim("user", "test_user")
//                .compact();
//
//        // 2초 딜레이
//        Thread.sleep(2000L);
//
//        // when
//        InvalidParameterException ex = assertThrows(
//                InvalidParameterException.class
//                , () -> jwtService.isUsable(jwt));
//        // then
//        assertThat(ex.getMessage()).isEqualTo("유효하지 않은 토큰입니다");
//    }

}