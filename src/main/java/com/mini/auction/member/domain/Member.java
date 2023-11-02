package com.mini.auction.member.domain;

import lombok.*;

/*
    Entity 가 아님. 실제 DB 와 직접적인 연관이 없음.
    로직 안에서만 사용된다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member {

    private String name;

    private String password;

    private String email;

    private String phoneNo;
// TODO: common에서 Money 객체로 가져오기
    private int money = 0;

    public Member(String name, String password, String email, String phoneNo) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
