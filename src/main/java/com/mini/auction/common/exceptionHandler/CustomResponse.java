package com.mini.auction.common.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomResponse {
    private String code;
    private String message;

    public CustomResponse(
        ExceptionCode exceptionCode
    ){
        this.code = exceptionCode.name();
        this.message = exceptionCode.getMessage();
    }
}
