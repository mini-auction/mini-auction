package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import lombok.Getter;

//로그인 실패 혹은 토큰이 없을 때.
@Getter
public class NotFoundAuthException extends IllegalArgumentException {

    private final CustomResponse errorMessage;

    public NotFoundAuthException(CustomResponse errorMessage) {
        this.errorMessage = errorMessage;
    }
}
