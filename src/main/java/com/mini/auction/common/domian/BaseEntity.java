package com.mini.auction.common.domian;

import com.mini.auction.common.Utils;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    private boolean isDeleted = false;

    private String id = Utils.customUUID();
}
