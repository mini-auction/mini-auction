package com.mini.auction.common.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    protected Boolean isDeleted;
    protected String id;
}
