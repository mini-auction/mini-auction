package com.mini.auction.auction.application.service;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
import com.mini.auction.auction.application.port.in.AuctionService;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Override
    public void createAuction(CreateAuction request) {
        //경매 약관 동의
        if (!request.isAgree()) {
        }
        //경매 생성
        //
    }
}
