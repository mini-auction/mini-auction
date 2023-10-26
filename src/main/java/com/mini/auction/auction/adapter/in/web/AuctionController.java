package com.mini.auction.auction.adapter.in.web;

import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
import com.mini.auction.auction.application.port.in.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auction")
@RestController
class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("")
    void createAuction(@RequestBody @Valid CreateAuction request){
        auctionService.createAuction(request);
    }
}
