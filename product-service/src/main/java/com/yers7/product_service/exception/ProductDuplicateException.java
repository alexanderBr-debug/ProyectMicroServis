package com.yers7.product_service.exception;

public class ProductDuplicateException extends  RuntimeException{
    public ProductDuplicateException(String msj){
        super(msj);
    }
}
