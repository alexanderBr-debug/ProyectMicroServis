package com.yers7.product_service.objectValo;

import com.yers7.product_service.exception.StrockCannotBeNegativeException;

public record ProductStock(Integer value) {
    public ProductStock{
        if (value < 0 || value == null) {
            throw new StrockCannotBeNegativeException("stock cannot be negative");
        }
    }
}
