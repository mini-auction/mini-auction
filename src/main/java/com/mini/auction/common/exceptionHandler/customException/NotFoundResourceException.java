package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.ErrorResponse;
import lombok.Getter;

//조회되는 결과가 없을 때
@Getter
public class NotFoundResourceException extends IllegalArgumentException {

    private final ErrorResponse errorMessage;

    public NotFoundResourceException(ErrorResponse errorMessage) { this.errorMessage = errorMessage; }
}
