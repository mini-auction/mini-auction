package com.mini.auction.auction.application.service;

import com.mini.auction.auction.application.port.out.FindTermsPort;
import com.mini.auction.auction.application.port.out.TermsNullCheck;
import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TermsNullCheckImpl implements TermsNullCheck {

    private final FindTermsPort findTermsPort;

    @Override
    public void existsByIdAndIsDeletedFalse(String id) {
        if (!findTermsPort.existsByIdAndIsDeletedFalse(id)){
            throw new BadRequestException(new CustomResponse(ExceptionCode.E20000));
        }
    }
}
