package com.yers7.product_service.objectValue;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.yers7.product_service.exception.PriceCannotBeNegativeException;

public record ProductPrice(BigDecimal value) {
    @JsonCreator (mode = JsonCreator.Mode.DELEGATING)
    public ProductPrice{
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new PriceCannotBeNegativeException("price cannot be negative ");
        }
        
    }
}