package com.yers7.product_service.dto;



import com.yers7.product_service.objectValo.ProductPrice;
import com.yers7.product_service.objectValo.ProductStock;

import jakarta.validation.constraints.NotBlank;


public record ProductRequestDto(

    @NotBlank (message = "obligatorio")
    String name,

    ProductPrice price,

    Long id,

    ProductStock stock


) {
    
}
