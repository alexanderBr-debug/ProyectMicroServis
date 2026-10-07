package com.yers7.sales_service.domain.exception;

public class SaleIdNotNullException extends RuntimeException {
    public SaleIdNotNullException(String message){
        super(message);
    }
}
