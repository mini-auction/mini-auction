package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.common.domian.BaseJpaEntity;
import com.mini.auction.common.enums.AuctionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
class AuctionEntity extends BaseJpaEntity {

    @Comment("판매자 id")
    @Column(length = 100, nullable = false)
    private String sellerId;

    @Comment("제목")
    @Column(length = 100, nullable = false)
    private String title;

    @Comment("내용")
    @Column(length = 100, nullable = false)
    private String contents;

    @Comment("경매 오픈일")
    @Column(nullable = false)
    private LocalDateTime openDateTime;

    @Comment("경매 마감일")
    @Column(nullable = false)
    private LocalDateTime closedDateTime;

    @Comment("최소 입찰가")
    @Column(nullable = false)
    private int minimumBidAmount = 0;

    @Comment("경매 상태")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionState state = AuctionState.WAITING;

    @Comment("댓글")
    @Column(name = "comments_id")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auction")
    private List<CommentsEntity> comments = new ArrayList<CommentsEntity>();

}
