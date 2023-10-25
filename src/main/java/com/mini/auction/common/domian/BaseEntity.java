package com.mini.auction.common.domian;

import jakarta.persistence.Column;
import com.mini.auction.common.Utils;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends Timestamped {
    @Column
    private boolean isDeleted = false;

    @Id
    @Column(length = 15)
    private String id = Utils.customUUID();

    public void setField(
        boolean isDeleted,
        String id
    ){
        this.isDeleted = isDeleted;
        this.id = id;
    }
}
