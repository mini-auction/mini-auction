package com.mini.auction.auction.adapter.in.web;

import com.mini.auction.auction.adapter.in.web.dto.AuctionRes;
import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.application.port.in.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/{id}")
    AuctionRes getAuctionDetail(
        @PathVariable String id
    ){
        return auctionService.getAuctionDetail(id);
    }

    @DeleteMapping("/{id}")
    void removeAuction(
        @PathVariable String id
    ){
        auctionService.removeAuction(id);
    }
    @GetMapping("/list/waiting")
    Page<AuctionsRes> getWaitingAuctionsPage(
        Pageable pageable,
        @Valid AuctionsReq auctionsReq
    ){
        return auctionService.getWaitingAuctionsPage(pageable, auctionsReq);
    }

}
