package com.yers7.sales_service.infrastructure.adapters.out.messaging.event;

import java.time.Instant;

public record SaleCancelledEvent(
        String eventId,
        String saleId,
        String reason,
        Instant cancelledAt
) {}