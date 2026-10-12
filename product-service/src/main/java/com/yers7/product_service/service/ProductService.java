package com.yers7.product_service.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.yers7.product_service.dto.ProductRequestDto;
import com.yers7.product_service.dto.ProductResponseDto;
import com.yers7.product_service.exception.ProductDuplicateException;
import com.yers7.product_service.model.ProductEntity;
import com.yers7.product_service.model.ProductStatus;
import com.yers7.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductResponseDto create(ProductRequestDto request){

        productRepository.findById(request.id())
        .ifPresent((product) -> {
            throw new ProductDuplicateException("the product already");
        });

        ProductEntity entity = new ProductEntity().builder()
        .createdAt(Instant.now())
        .id(request.id())
        .name(request.name())
        .stock(request.stock())
        .price(request.price())
        .status(ProductStatus.ACTIVE)
        .build();

        productRepository.save(entity);

        ProductResponseDto responseDto = new ProductResponseDto();
        respons
        
    }
}
