package com.yers7.product_service.messaging.dto;

import java.math.BigDecimal;

import java.util.List;

public record SaleCreatedEvent(
        String eventId,
        String saleId,
        String customerId,
        BigDecimal totalAmount,
        String createdAt,
        List<SaleItemEvent> items
) {
    public record SaleItemEvent(
            String productId,
            int quantity
    ) {}
}