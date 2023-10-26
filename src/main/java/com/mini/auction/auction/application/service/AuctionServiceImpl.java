package com.mini.auction.auction.application.service;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
import com.mini.auction.auction.application.port.in.AuctionService;
import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.auction.application.port.out.AuctionTermsMappingLogPort;
import com.mini.auction.auction.application.port.out.FindTermsPort;
import com.mini.auction.auction.domain.Auction;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.member.application.port.out.FindMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class AuctionServiceImpl implements AuctionService {
    private final AuctionTermsMappingLogPort auctionTermsMappingLogPort;
    private final AuctionPort auctionPort;
    private final FindMemberPort findMemberPort;
    private final FindTermsPort findTermsPort;

    @Transactional
    @Override
    public void createAuction(CreateAuction request) {
        //sellerId 체크
        if (!findMemberPort.existById(request.getSellerId())) {
            throw new BadRequestException(new ErrorResponse(ErrorCode.E10000));
        }
        //termsId 체크
        if (!findTermsPort.existById(request.getTermsId())){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E20000));
        }
        //경매 약관 동의
        if (!request.getIsAgree()) {
            throw new BadRequestException(new ErrorResponse(ErrorCode.E20001));
        }
        //경매 시작일과 종료일이 동일한지 체크
        if (request.getOpenDateTime().equals(request.getClosedDateTime())){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E30001));
        }
        //경매 생성
        Auction auction = Auction.createAuction(request);
        auctionPort.save(auction);
        //약관 동의 저장
        auctionTermsMappingLogPort.save(auction.getId(), request.getTermsId());
    }
}
