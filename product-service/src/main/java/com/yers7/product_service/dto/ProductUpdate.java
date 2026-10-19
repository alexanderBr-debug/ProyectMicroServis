package com.yers7.product_service.dto;



import com.yers7.product_service.model.ProductStatus;
import com.yers7.product_service.objectValue.ProductPrice;
import com.yers7.product_service.objectValue.ProductStock;


public record ProductUpdate(
    String name,
    ProductPrice price,
    ProductStock stock,
    ProductStatus status
) {
    
}
