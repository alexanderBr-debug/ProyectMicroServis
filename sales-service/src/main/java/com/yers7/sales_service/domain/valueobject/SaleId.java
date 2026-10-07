package com.yers7.sales_service.domain.valueobject;

import java.util.UUID;

import com.yers7.sales_service.domain.exception.SaleIdNotNullException;

public record SaleId(UUID value) {
    public SaleId {
        if (value == null) throw new SaleIdNotNullException("Sale id not null");
    }
}

