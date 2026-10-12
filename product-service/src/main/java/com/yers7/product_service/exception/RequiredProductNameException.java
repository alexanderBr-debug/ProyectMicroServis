package com.yers7.product_service.exception;

public class RequiredProductNameException extends RuntimeException{
    public RequiredProductNameException(String msj){
        super(msj);
    }
}
