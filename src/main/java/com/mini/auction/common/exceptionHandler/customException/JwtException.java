package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.ErrorResponse;
import lombok.Getter;

//모든 잘 못된 요청에 대한 익셉션
@Getter
public class JwtException extends Exception {
    private final ErrorResponse errorMessage;

    public JwtException(ErrorResponse errorMessage){
        this.errorMessage = errorMessage;
    }
}
