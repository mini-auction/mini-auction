package com.mini.auction.auction.adapter.out.persistence.infrastructure;

import com.mini.auction.auction.domain.Terms;

public interface TermsCustomRepository {
    Terms getTerms(String id);
}
