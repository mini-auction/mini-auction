package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.auction.domain.AuctionDetail;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AuctionPersistenceAdapter implements AuctionPort {
    private final AuctionRepository auctionRepository;
    private final AuctionEntityMapper auctionEntityMapper;

    @Override
    public String save(AuctionReq.CreateAuction req) {
        AuctionEntity auctionEntity = auctionEntityMapper.mapToEntity(auctionEntityMapper.mapToDomain(req));
        auctionRepository.save(auctionEntity);
        return auctionEntity.getId();
    }

    @Override
    public boolean existById(String id) {
        return auctionRepository.existsById(id);
    }

    @Override
    public void updateDetailById(String id, AuctionReq req) {
        AuctionDetail detail = auctionEntityMapper.mapToAuctionDetail(
            req.getTitle(),
            req.getContents(),
            req.getOpenDateTime(),
            req.getClosedDateTime(),
            req.getMinimumBidAmount()
        );
        auctionRepository.updateDetail(id, detail);
    }

    @Override
    public Auction findById(String id) {
        AuctionEntity auctionEntity = auctionRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorResponse(ErrorCode.E00001)));
        return auctionEntityMapper.mapToDomain(auctionEntity);
    }


}
