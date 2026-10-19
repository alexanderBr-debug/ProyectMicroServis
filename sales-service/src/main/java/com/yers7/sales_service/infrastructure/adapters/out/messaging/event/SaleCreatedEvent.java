package com.yers7.sales_service.infrastructure.adapters.out.messaging.event;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SaleCreatedEvent(
        @JsonProperty ("eventId") String eventId,
        @JsonProperty ("saleId") String saleId,
        @JsonProperty ("customerId") String customerId,
        @JsonProperty ("totalAmount") BigDecimal totalAmount,
        @JsonProperty ("createdAt") String createdAt,
        @JsonProperty ("items") List<SaleItemEvent> items
) {

        @JsonCreator 
        public SaleCreatedEvent{}

    public record SaleItemEvent(
        @JsonProperty ("productId") String productId,
        @JsonProperty ("quantity")  int quantity
    ) {
        @JsonCreator 
        public SaleItemEvent{}
    }
}