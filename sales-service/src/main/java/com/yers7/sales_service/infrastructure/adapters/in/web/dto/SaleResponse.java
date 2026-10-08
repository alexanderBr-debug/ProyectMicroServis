package com.yers7.sales_service.infrastructure.adapters.in.web.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record SaleResponse(
        String saleId,
        String customerId,
        String status,
        BigDecimal totalAmount,
        String currency,
        Instant createdAt
) {}

