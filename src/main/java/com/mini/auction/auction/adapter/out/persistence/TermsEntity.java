package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.common.BaseEntity;
import com.mini.auction.common.enums.TermsType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "terms")
class TermsEntity extends BaseEntity {

    @Column
    private String contents;

    @Column
    private TermsType type;
}
