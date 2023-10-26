package com.mini.auction.common.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse extends CustomResponse {
    private Object detail;

    public ExceptionResponse(
        ExceptionCode exceptionCode,
        Object value
    ){
        super(exceptionCode);
        this.detail = value;
    }
}