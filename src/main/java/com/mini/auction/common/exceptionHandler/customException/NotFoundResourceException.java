package com.mini.auction.common.exceptionHandler.customException;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import lombok.Getter;

//조회되는 결과가 없을 때
@Getter
public class NotFoundResourceException extends IllegalArgumentException {

    private final CustomResponse errorMessage;

    public NotFoundResourceException(CustomResponse errorMessage) { this.errorMessage = errorMessage; }
}
