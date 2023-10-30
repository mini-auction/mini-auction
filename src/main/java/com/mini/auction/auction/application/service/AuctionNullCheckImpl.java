package com.mini.auction.auction.application.service;

import com.mini.auction.auction.application.port.out.AuctionNullCheck;
import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuctionNullCheckImpl implements AuctionNullCheck {

    private final AuctionPort auctionPort;

    @Override
    public void existsById(String id) {
        if (!auctionPort.existById(id)){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E00001));
        }
    }
}
