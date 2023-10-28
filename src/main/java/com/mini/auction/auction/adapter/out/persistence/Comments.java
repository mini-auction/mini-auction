package com.mini.auction.auction.adapter.out.persistence;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.mini.auction.common.domian.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Entity
public class Comments extends BaseJpaEntity {
    @Column
    @Comment("내용")
    private String contents;

    @Column(length = 15)
    @Comment("작성자 id")
    private String memberId;

}
