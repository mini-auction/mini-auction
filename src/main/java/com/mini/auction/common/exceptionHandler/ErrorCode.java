package com.mini.auction.common.exceptionHandler;

import lombok.Getter;

@Getter
public enum ErrorCode {

    E00001("E00001", "필수 파라미터가 없습니다."),
    E00002("E00002", "파라미터 타입이 일치하지 않습니다."),
    E00003("E00003", "요청 데이터가 올바르지 않습니다."),
    E00004("E00004", "만료된 토큰입니다."),
    E00005("E00005", "유효하지 않은 토큰입니다.");



    private final String code;
    private final String message;


    public String getMessage(){
        return this.message;
    }

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
