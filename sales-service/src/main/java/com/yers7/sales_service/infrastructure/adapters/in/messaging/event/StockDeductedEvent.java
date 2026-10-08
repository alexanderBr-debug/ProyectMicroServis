package com.yers7.sales_service.infrastructure.adapters.in.messaging.event;

import java.time.Instant;

public record StockDeductedEvent(
        String eventId,
        String saleId,
        Instant timestamp
) {}