package com.mini.auction.auction.application.port.out;

public interface FindTermsPort {
    boolean existsByIdAndIsDeletedFalse(String id);
}
