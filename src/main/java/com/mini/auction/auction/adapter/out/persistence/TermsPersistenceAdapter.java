package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.application.port.out.FindTermsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TermsPersistenceAdapter implements FindTermsPort {
    private final TermsRepository termsRepository;
    @Override
    public boolean existsByIdAndIsDeletedFalse(String id) {
        return termsRepository.existsByIdAndIsDeletedFalse(id);
    }
}
