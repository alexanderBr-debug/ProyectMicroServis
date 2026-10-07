package com.yers7.sales_service.domain.exception;

public class CannotAddUpDifferentCurrenciesException extends RuntimeException {
    public CannotAddUpDifferentCurrenciesException(String message){
        super(message);
    }
}
