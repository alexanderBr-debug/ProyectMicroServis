package com.yers7.sales_service.infrastructure.adapters.out.messaging.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record SaleCreatedEvent(
        String eventId,
        String saleId,
        String customerId,
        BigDecimal totalAmount,
        Instant createdAt,
        List<SaleItemEvent> items
) {
    public record SaleItemEvent(
            String productId,
            int quantity
    ) {}
}