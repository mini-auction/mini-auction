package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.domian.Money;
import com.mini.auction.common.enums.AuctionState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
class AuctionEntityMapper {
    private CommentsEntityMapper commentsEntityMapper;

    AuctionEntity mapToEntity(Auction auction){
        return new AuctionEntity(
            auction.getSellerId(),
            auction.getDetail().getTitle(),
            auction.getDetail().getContents(),
            auction.getDetail().getOpenDateTime(),
            auction.getDetail().getClosedDateTime(),
            auction.getDetail().getMinimumBidAmount().getAmount(),
            auction.getState(),
            Collections.emptyList()
        );
    }

    Auction mapToDomain(AuctionReq.CreateAuction createAuction){
        return new Auction(
            createAuction.getSellerId(),
            mapToAuctionDetail(
                createAuction.getTitle(),
                createAuction.getContents(),
                createAuction.getOpenDateTime(),
                createAuction.getClosedDateTime(),
                new Money(createAuction.getMinimumBidAmount())
            ),
            AuctionState.WAITING,
            Collections.emptyList()
        );
    }

    Auction mapToDomain(AuctionEntity auctionEntity) {
        return new Auction(
            auctionEntity.getSellerId(),
            mapToAuctionDetail(
                auctionEntity.getTitle(),
                auctionEntity.getContents(),
                auctionEntity.getOpenDateTime(),
                auctionEntity.getClosedDateTime(),
                new Money(auctionEntity.getMinimumBidAmount())
            ),
            auctionEntity.getState(),
            auctionEntity.getComments().stream()
                .map(
                    comments -> commentsEntityMapper.mapToDomain(comments)
                ).collect(Collectors.toList()),
            auctionEntity.getId(),
            auctionEntity.isDeleted()
        );
    }

    AuctionDetail mapToAuctionDetail(
        String title,
        String contents,
        LocalDateTime openDateTime,
        LocalDateTime closedDateTime,
        Money minimumBidAmount
    ){
        return new AuctionDetail(
            title,
            contents,
            openDateTime,
            closedDateTime,
            minimumBidAmount
        );
    }

}
