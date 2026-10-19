package com.yers7.product_service.dto;

import java.math.BigDecimal;

public record ClientResponseDto(
        String id,
        String name,
        BigDecimal price,
        Integer availableStock
) {}