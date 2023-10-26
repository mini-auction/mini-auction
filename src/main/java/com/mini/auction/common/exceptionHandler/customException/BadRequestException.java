package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import lombok.Getter;

//모든 잘 못된 요청에 대한 익셉션
@Getter
public class BadRequestException extends IllegalArgumentException {
    private final CustomResponse errorMessage;

    public BadRequestException(CustomResponse errorMessage){
        this.errorMessage = errorMessage;
    }
}
