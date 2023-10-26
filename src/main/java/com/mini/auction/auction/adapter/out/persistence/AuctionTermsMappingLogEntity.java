package com.mini.auction.auction.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AuctionTermsMappingLogEntity.AuctionTermsMappingLogId.class)
public class AuctionTermsMappingLogEntity {

    @Id
    @Column(length = 15)
    @Comment("경매 id")
    private String auctionId;

    @Id
    @Column(length = 15)
    @Comment("약관 id")
    private String termsId;

    @Column(nullable = false)
    @Comment("생성일")
    private final LocalDateTime createDateTime = LocalDateTime.now();

    @Data
    public static class AuctionTermsMappingLogId implements Serializable {
        private String auctionId;
        private String termsId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuctionTermsMappingLogEntity auctionTermsMappingLog) {
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
