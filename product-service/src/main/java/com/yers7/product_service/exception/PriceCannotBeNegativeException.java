package com.yers7.product_service.exception;

public class PriceCannotBeNegativeException extends  RuntimeException{
    public PriceCannotBeNegativeException(String msj){
        super(msj);
    }
}
