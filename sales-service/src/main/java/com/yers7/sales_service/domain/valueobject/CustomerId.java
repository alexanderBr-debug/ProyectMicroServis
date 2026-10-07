package com.yers7.sales_service.domain.valueobject;

import com.yers7.sales_service.domain.exception.CustomerIsInvalidException;

public record CustomerId(String value) {
    public CustomerId{
        if (value == null || value.isBlank()) {
            throw new CustomerIsInvalidException("customer is invalid");
        }
    }
}
