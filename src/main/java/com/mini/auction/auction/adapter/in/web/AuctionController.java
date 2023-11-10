package com.mini.auction.auction.adapter.in.web;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.application.port.in.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auction")
@RestController
class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("")
    void createAuction(@RequestBody @Valid AuctionReq.CreateAuction req){
        auctionService.createAuction(req);
    }

    @PutMapping("/{id}")
    void updateAuction(
        @PathVariable String id,
        @RequestBody @Valid AuctionReq req
    ){
        auctionService.updateAuction(id, req);
    }

    @GetMapping("/list/waiting")
    Page<AuctionsRes> getWaitingAuctionsPage(Pageable pageable){
        return auctionService.getWaitingAuctionsPage(pageable);
    }

}
