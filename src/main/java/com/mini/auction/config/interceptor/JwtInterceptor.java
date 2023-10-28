package com.mini.auction.config.interceptor;

import com.mini.auction.common.exceptionHandler.customException.JwtException;
import com.mini.auction.config.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws JwtException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        return jwtService.isUsable(token);
    }



}
