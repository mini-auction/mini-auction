package com.mini.auction.config.interceptor;

import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.customException.NotFoundAuthException;
import com.mini.auction.config.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            logger.error("Authorization is null. RequestUri : " + request.getRequestURI());
            throw new NotFoundAuthException(new CustomResponse(ExceptionCode.E00006));
        }
        return jwtService.isUsable(token);
    }

}
