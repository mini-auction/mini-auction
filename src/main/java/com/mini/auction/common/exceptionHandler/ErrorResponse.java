package com.mini.auction.common.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String code;
    private String message;
    private Object value;

    public ErrorResponse(
        ErrorCode errorCode,
        Object value
    ){
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.value = value;
    }
}