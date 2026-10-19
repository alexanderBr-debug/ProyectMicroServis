package com.yers7.product_service.dto;




import com.yers7.product_service.objectValue.ProductPrice;
import com.yers7.product_service.objectValue.ProductStock;


public record ProductResponseDto(
    Long id,
    String name,
    ProductPrice price,
    ProductStock stock
) {
    
}
