package com.mini.auction.auction.application.service;

import com.mini.auction.auction.application.port.out.FindTermsPort;
import com.mini.auction.auction.application.port.out.TermsNullCheck;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TermsNullCheckImpl implements TermsNullCheck {

    private final FindTermsPort findTermsPort;

    @Override
    public void existsById(String id) {
        if (!findTermsPort.existById(id)){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E20000));
        }
    }
}
