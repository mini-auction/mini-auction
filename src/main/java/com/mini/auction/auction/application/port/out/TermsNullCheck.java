package com.mini.auction.auction.application.port.out;

public interface TermsNullCheck {
    void existsByIdAndIsDeletedFalse(String id);

}
