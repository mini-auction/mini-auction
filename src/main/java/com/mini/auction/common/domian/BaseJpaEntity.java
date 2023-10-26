package com.mini.auction.common.domian;

import com.mini.auction.common.Utils;
import com.mini.auction.common.domian.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseJpaEntity extends Timestamped {
    @Column
    private boolean isDeleted;

    @Id
    @Column(length = 15)
    private String id;

    public void setField(String id, boolean isDeleted){ //함수명이 적절한지 잘 모르겠음.
        this.id = id;
        this.isDeleted = isDeleted;
    }
}
