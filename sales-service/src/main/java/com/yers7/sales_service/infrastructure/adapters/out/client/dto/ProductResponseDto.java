package com.yers7.sales_service.infrastructure.adapters.out.client.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
        String id,
        String name,
        BigDecimal price,
        Integer availableStock
) {}