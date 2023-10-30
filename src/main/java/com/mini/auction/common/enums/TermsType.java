package com.mini.auction.common.enums;

public enum TermsType {
    AUCTION("경매"), BIDDER("입찰");

    private final String value;

    public String getValue(){ return this.value; }

    TermsType(String type){ this.value = type; }

}
