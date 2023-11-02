package com.mini.auction.member.adapter.out.persistence.infrastructure;

import com.mini.auction.config.jwt.JwtToken;
import com.mini.auction.member.adapter.in.web.dto.LoginReq;


public interface MemberCustomRepository {
    JwtToken getMemberByEmailAndPassword(LoginReq req);
}
