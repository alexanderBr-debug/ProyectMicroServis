package com.yers7.sales_service.domain.valueobject;

import com.yers7.sales_service.domain.exception.ProductIdRequeridException;

public record ProductId(String value) {
    
    public ProductId{
        if (value == null || value.isBlank()){
            throw new ProductIdRequeridException("required id");
        }
    }
}
