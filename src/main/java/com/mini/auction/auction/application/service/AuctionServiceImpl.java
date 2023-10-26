package com.mini.auction.auction.application.service;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
import com.mini.auction.auction.application.port.in.AuctionService;
import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.auction.application.port.out.AuctionTermsMappingLogPort;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class AuctionServiceImpl implements AuctionService {
    private final AuctionTermsMappingLogPort auctionTermsMappingLogPort;
    private final AuctionPort auctionPort;

    @Override
    public void createAuction(CreateAuction request) {
        //sellerId 체크

        //termsId 체크


        //경매 약관 동의
        if (!request.getIsAgree()) {
            throw new BadRequestException(new ErrorResponse(ErrorCode.E00001));
        }

        //경매 시작일과 종료일이 동일한지 체크
        if (request.getOpenDateTime().equals(request.getClosedDateTime())){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E00001));
        }

        //경매 생성
        Auction auction = new Auction(
            request.getSellerId(),
            request.getTitle(),
            request.getContents(),
            request.getOpenDateTime(),
            request.getClosedDateTime(),
            request.getMinimumBidAmount()
        );
        auctionPort.save(auction);
        //약관 동의 저장
        auctionTermsMappingLogPort.save(auction.getId(), request.getTermsId());
    }
}
