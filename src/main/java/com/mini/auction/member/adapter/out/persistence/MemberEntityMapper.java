package com.mini.auction.member.adapter.out.persistence;

import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import com.mini.auction.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
class MemberEntityMapper {

    MemberEntity mapToEntity(Member member){
        return new MemberEntity(
                member.getName(),
                member.getPassword(),
                member.getEmail(),
                member.getPhoneNo(),
                member.getMoney()
        );
    }

    Member mapToMember(RegistrationInfoReq req){
        return new Member(
                req.getName(),
                req.getPassword(),
                req.getEmail(),
                req.getPhoneNo()
        );
    }

}
