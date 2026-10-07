package com.yers7.sales_service.domain.exception;

public class AmountCannotBeNegativeException extends RuntimeException {
    public AmountCannotBeNegativeException(String message){
        super(message);
    }
}
