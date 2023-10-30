package com.mini.auction.member.application.port.out;

public interface MemberNullCheck {
    void existsByIdAndIsDeletedFalse(String id);

}
