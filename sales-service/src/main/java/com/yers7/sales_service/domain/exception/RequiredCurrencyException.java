package com.yers7.sales_service.domain.exception;

public class RequiredCurrencyException extends RuntimeException {
    public RequiredCurrencyException(String message){
        super(message);
    }
}
