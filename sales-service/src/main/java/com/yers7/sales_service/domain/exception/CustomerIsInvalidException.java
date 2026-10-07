package com.yers7.sales_service.domain.exception;

public class CustomerIsInvalidException extends RuntimeException {
    public CustomerIsInvalidException(String message){
        super(message);
    }
}
