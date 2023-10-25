package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.domain.Terms;
import org.springframework.stereotype.Component;

@Component
class TermsMapper {

    TermsEntity mapToEntity(Terms terms){
        return new TermsEntity(
            terms.getContents(),
            terms.getType()
        );
    }
}
