package com.mini.auction.member.application.service;

import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.member.application.port.out.FindMemberPort;
import com.mini.auction.member.application.port.out.MemberNullCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class MemberNullCheckImpl implements MemberNullCheck {

    private final FindMemberPort findMemberPort;

    @Override
    public void existsById(String id) {
        if (!findMemberPort.existById(id)) {
            throw new BadRequestException(new ErrorResponse(ErrorCode.E10000));
        }
    }
}
