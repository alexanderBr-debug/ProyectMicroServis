package com.yers7.sales_service.infrastructure.adapters.in.messaging.event;

import java.time.Instant;

public record PaymentFailedEvent(
        String eventId,
        String saleId,
        String reason,
        Instant timestamp
) {}