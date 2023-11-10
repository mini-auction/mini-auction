package com.mini.auction.auction.adapter.in.web;

import com.mini.auction.auction.adapter.in.web.dto.AuctionReq;
import com.mini.auction.auction.adapter.in.web.dto.AuctionsRes;
import com.mini.auction.auction.application.port.in.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/waiting/page")
    Page<AuctionsRes> getWaitingAuctionsPage(
            @PageableDefault(
                    sort = "createDateTime", direction = Sort.Direction.DESC
            ) Pageable pageable){
        System.out.println("페이지: " + pageable.getPageSize());
        return auctionService.getWaitingAuctionsPage(pageable);
    }

    @GetMapping("/waiting/list")
    List<AuctionsRes> getWaitingAuctionsList(
            @PageableDefault(
                    sort = "createDateTime", direction = Sort.Direction.DESC
            ) Pageable pageable){
        System.out.println("페이지: " + pageable.getPageSize());
        return auctionService.getWaitingAuctionsList(pageable);
    }

}
