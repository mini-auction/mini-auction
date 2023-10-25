package com.mini.auction.config.interceptor;

import com.mini.auction.common.exceptionHandler.customException.JwtException;
import com.mini.auction.config.jwt.JwtService;
import com.mini.auction.config.jwt.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTH = "Authorization";

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws JwtException {

        final String token = request.getHeader(HEADER_AUTH);

        if (token != null && jwtService.isUsable(token)) {
            return true;
        } else {
            throw new UnauthorizedException();
        }
    }



}
