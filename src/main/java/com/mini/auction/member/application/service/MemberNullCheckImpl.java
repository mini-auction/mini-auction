package com.mini.auction.member.application.service;

import com.mini.auction.common.exceptionHandler.CustomResponse;
import com.mini.auction.common.exceptionHandler.ExceptionCode;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.member.application.port.out.FindMemberPort;
import com.mini.auction.member.application.port.out.MemberNullCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberNullCheckImpl implements MemberNullCheck {

    private final FindMemberPort findMemberPort;

    @Override
    public void existsById(String id) {
        if (!findMemberPort.existById(id)) {
            throw new BadRequestException(new CustomResponse(ExceptionCode.E10000));
        }
    }
}
