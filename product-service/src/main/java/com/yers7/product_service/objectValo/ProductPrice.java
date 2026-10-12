package com.yers7.product_service.objectValo;

import java.math.BigDecimal;

import com.yers7.product_service.exception.PriceCannotBeNegativeException;

public record ProductPrice(BigDecimal value) {
    public ProductPrice{
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new PriceCannotBeNegativeException("price cannot be negative ");
        }
        
    }
}
