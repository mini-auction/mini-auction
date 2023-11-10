package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.common.domian.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@Entity
class CommentsEntity extends BaseJpaEntity {
    @Column
    @Comment("내용")
    private String contents;

    @Column(length = 15)
    @Comment("작성자 id")
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    @Comment("경매글")
    private AuctionEntity auction;

}
