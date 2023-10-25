package com.mini.auction.common;

import com.mini.auction.common.exceptionHandler.customException.JwtException;
import com.mini.auction.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtTestController {
    private final JwtService jwtService;

    @GetMapping("/jwt")
    public String creatJwt(){
        record MemberInfo(String id, String name){}
        MemberInfo memberInfo = new MemberInfo("test_id", "test_name");
        return jwtService.create(memberInfo);
    }

    @GetMapping("/claims")
    public Map<String, Object> getJwt() throws JwtException {
        return jwtService.getClaims("memberInfo");
    }
}
