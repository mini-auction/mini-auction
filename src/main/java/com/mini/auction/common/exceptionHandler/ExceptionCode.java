package com.mini.auction.common.exceptionHandler;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    /**
     * E0000x : common
     * E01xxx : money
     * E1xxxx : member
     * E2xxxx : terms
     * E3xxxx : auction
     * E4xxxx : auctionTermsMappingLog
     *
     */

    E00001("필수 파라미터가 없습니다."),
    E00002("파라미터 타입이 일치하지 않습니다."),
    E00003("요청 데이터가 올바르지 않습니다."),
    E00004("만료된 토큰입니다."),
    E00005("유효하지 않은 토큰입니다."),
    E00006("토큰이 없습니다."),

    E01000("금액은 0보다 작을 수 없습니다."),


    E10000("id와 일치하는 회원이 없습니다."),
    E10001("등록되지 않은 email 입니다."),
    E10002("이미 존재하는 email 입니다."),
    E10003("비밀번호가 다릅니다."),

    E20000("id와 일치하는 약관이 없습니다."),
    E20001("약관 동의가 필요합니다."),

    E30000("id와 일치하는 경매가 없습니다."),
    E30001("경매 시작 시각과 종료 시각은 일치할 수 없습니다."),
    E30002("마지막 조회 날짜가 시작 조회 날짜보다 작을 수 없습니다."),
    E30003("최대 조회 금액이 최소 조회 금액보다 작을 수 없습니다.");


    private final String code;
    private final String message;

    public String getMessage(){
        return this.message;
    }

    ExceptionCode(String message){
        this.code = this.name();
        this.message = message;
    }
}