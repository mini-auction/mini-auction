package com.mini.auction.auction.domain;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.Getter;

public class Money {
    final static int MIN = 0;

    @Getter
    private final int amount;

    public Money(int value){
        if (value < MIN){
            throw new BadRequestException(new CustomResponse(ExceptionCode.E01000));
        }
        this.amount = value;
    }

    public Money add(Money x, Money y){
        return new Money(x.amount + y.amount);
    }

}
