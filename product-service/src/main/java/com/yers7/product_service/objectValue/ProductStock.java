package com.yers7.product_service.objectValue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.yers7.product_service.exception.StrockCannotBeNegativeException;

public record ProductStock(Integer value) {
    @JsonCreator (mode = JsonCreator.Mode.DELEGATING)
    public ProductStock{
        if (value < 0 || value == null) {
            throw new StrockCannotBeNegativeException("stock cannot be negative");
        }
    }
}