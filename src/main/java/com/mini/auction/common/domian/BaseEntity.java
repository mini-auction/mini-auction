package com.mini.auction.common.domian;

import com.mini.auction.common.Utils;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity extends Timestamped {
    private final boolean isDeleted = false;

    @Id
    private final String id = Utils.customUUID();

}
