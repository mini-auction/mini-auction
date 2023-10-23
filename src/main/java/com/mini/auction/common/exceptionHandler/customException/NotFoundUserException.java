package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.ErrorResponse;
import lombok.Getter;

//로그인 실패 시.
@Getter
public class NotFoundUserException extends IllegalArgumentException {

    private final ErrorResponse errorMessage;

    public NotFoundUserException(ErrorResponse errorMessage) {
        this.errorMessage = errorMessage;
    }
}
