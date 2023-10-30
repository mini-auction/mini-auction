package com.mini.auction.auction.application.service;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.application.port.in.AuctionService;
import com.mini.auction.auction.application.port.out.AuctionNullCheck;
import com.mini.auction.auction.application.port.out.AuctionPort;
import com.mini.auction.auction.application.port.out.AuctionTermsMappingLogPort;
import com.mini.auction.auction.application.port.out.TermsNullCheck;
import com.mini.auction.common.exceptionHandler.ErrorCode;
import com.mini.auction.common.exceptionHandler.ErrorResponse;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.member.application.port.out.MemberNullCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class AuctionServiceImpl implements AuctionService {
    private final AuctionTermsMappingLogPort auctionTermsMappingLogPort;
    private final AuctionPort auctionPort;
    private final MemberNullCheck memberNullCheck;
    private final TermsNullCheck termsNullCheck;
    private final AuctionNullCheck auctionNullCheck;

    @Transactional
    @Override
    public void createAuction(AuctionReq.CreateAuction req) {
        //sellerId 체크 //TODO : 토큰에서 꺼내오기.
        memberNullCheck.existsById(req.getSellerId());
        //termsId 체크
        termsNullCheck.existsById(req.getTermsId());
        //경매 약관 동의
        if (!req.getIsAgree()) {
            throw new BadRequestException(new ErrorResponse(ErrorCode.E20001));
        }
        //경매 시작일과 종료일이 동일한지 체크
        if (req.getOpenDateTime().equals(req.getClosedDateTime())){
            throw new BadRequestException(new ErrorResponse(ErrorCode.E30001));
        }
        //경매 생성
        String auctionId = auctionPort.save(req);
        //약관 동의 저장
        auctionTermsMappingLogPort.save(auctionId, req.getTermsId());
    }

    @Override
    public void updateAuction(String id, AuctionReq req) {
        //TODO : 토큰에서 사용자 id 꺼낸 후 , 게시글쓴이가 맞는지 체크.
        auctionNullCheck.existsById(id);
        auctionPort.updateDetailById(id, req);
    }

}
