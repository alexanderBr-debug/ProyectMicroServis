package com.yers7.product_service.dto;





import com.yers7.product_service.objectValue.ProductPrice;
import com.yers7.product_service.objectValue.ProductStock;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record ProductRequestDto(

    @NotBlank (message = "obligatorio")
    String name,

    @Valid 
    ProductPrice price,

    
    @Positive (message = "debe ser positivo")
    Long id,

     @Valid
    ProductStock stock


) {
    
}
