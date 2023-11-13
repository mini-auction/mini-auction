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
        @RequestBody @Valid AuctionsReq auctionsReq
    ){

        System.out.println("getMinOpenDate: " + auctionsReq.getMinOpenDate());
        System.out.println("getMinOpenDate 시작 시간: " + auctionsReq.getMinOpenDate().atStartOfDay());

        System.out.println("getMaxOpenDate: " + auctionsReq.getMaxOpenDate());
        System.out.println("getMaxOpenDate 마지막 시간: " + auctionsReq.getMaxOpenDate().atTime(LocalTime.MAX));

        return auctionService.getWaitingAuctionsPage(pageable, auctionsReq);
    }

}
