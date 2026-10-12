package com.yers7.product_service.exception;

public class StrockCannotBeNegativeException extends RuntimeException {
    public StrockCannotBeNegativeException(String msj){
        super(msj);
    }
}
