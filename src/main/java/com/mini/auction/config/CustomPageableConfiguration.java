package com.mini.auction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class CustomPageableConfiguration {

    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 30;
    public static final int MAX_PGE_SIZE = 30;

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizePageable() {
        return p -> {
            p.setFallbackPageable(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
            p.setMaxPageSize(MAX_PGE_SIZE);
        };
    }
}
