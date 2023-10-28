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
    private final boolean isDeleted = false;

    @Id
    @Column(length = 15)
    private final String id = Utils.customUUID();;

}
