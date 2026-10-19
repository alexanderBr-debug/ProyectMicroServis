package com.yers7.sales_service.domain.exception;

public class ProductIdRequeridException  extends  RuntimeException{
    public ProductIdRequeridException(String messaje){
        super(messaje);
    }
}
