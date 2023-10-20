package com.mini.auction.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BaseEntity extends Timestamped {
    private final boolean isDeleted = false;

    @Id
    private final String id = Utils.customUUID();

}
