package com.yers7.product_service.exception;

public class StatusConflictException extends RuntimeException {
    public StatusConflictException (String msj){
        super(msj);
    }
}
