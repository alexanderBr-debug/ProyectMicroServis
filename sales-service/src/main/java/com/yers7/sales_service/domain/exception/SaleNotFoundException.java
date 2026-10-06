package com.yers7.sales_service.domain.exception;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(String message){
        super(message);
    }
}
