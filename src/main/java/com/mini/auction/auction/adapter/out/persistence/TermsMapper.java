package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.domain.Terms;

class TermsMapper {

    Terms mapToTermsModel(TermsEntity termsEntity){
        Terms t = new Terms();

        return new Terms(
            termsEntity.getContents(),
            termsEntity.getType()
        );
    }
}
