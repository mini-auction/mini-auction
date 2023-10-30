package com.mini.auction.auction.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuctionTermsMappingLog {

    private String auctionId;

    private String termsId;

    private final LocalDateTime createDateTime = LocalDateTime.now();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuctionTermsMappingLog auctionTermsMappingLog) {
            return Objects.equals(this.auctionId, auctionTermsMappingLog.auctionId)
                && Objects.equals(this.termsId, auctionTermsMappingLog.termsId);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = auctionId != null ? auctionId.hashCode() : 0;
        result = 31 * result + (termsId != null ? termsId.hashCode() : 0);
        return result;
    }
}
