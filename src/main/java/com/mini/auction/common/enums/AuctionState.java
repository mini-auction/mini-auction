package com.mini.auction.common.enums;

public enum AuctionState {
    WAITING("대기"),
    IN_PROGRESS("진행중"),
    COMPLETED("완료"),
    AWARDED("낙찰"),
    FAILED("유찰");

    private final String value;
    public String getValue(){
        return value;
    }
    AuctionState(String state) {
        this.value = state;
    }

}
