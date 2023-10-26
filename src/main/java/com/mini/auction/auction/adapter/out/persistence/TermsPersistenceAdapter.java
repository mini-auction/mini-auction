package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.domain.Terms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TermsPersistenceAdapter {
    private final TermsRepository termsRepository;
    private final TermsMapper termsMapper;

    public void save(Terms terms){
        termsRepository.save(termsMapper.mapToEntity(terms));
    }
}
