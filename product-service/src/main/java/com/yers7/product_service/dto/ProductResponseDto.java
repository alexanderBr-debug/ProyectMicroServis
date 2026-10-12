package com.yers7.product_service.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
    Long id,
    String name,
    BigDecimal price
) {
    
}
