package com.mini.auction.auction.adapter.in.web.dto;

import com.mini.auction.auction.domain.AuctionDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AuctionRes {
    private String sellerId;
    private LocalDateTime createDateTime;
    private AuctionDetail detail;
    private List<CommentsInfo> commentsList;

    public AuctionRes(
        String sellerId,
        LocalDateTime createDateTime,
        AuctionDetail detail
    ) {
        this.sellerId = sellerId;
        this.createDateTime = createDateTime;
        this.detail = detail;
    }

    public void setCommentsList(
        List<CommentsInfo> commentsList
    ){
        this.commentsList = commentsList;
    }

}
