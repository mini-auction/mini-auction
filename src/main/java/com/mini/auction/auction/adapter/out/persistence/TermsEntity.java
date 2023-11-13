package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.common.domian.BaseJpaEntity;
import com.mini.auction.common.enums.TermsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
class TermsEntity extends BaseJpaEntity {

    @Column
    private String contents;

    @Column
    @Enumerated(EnumType.STRING)
    private TermsType type;
}
