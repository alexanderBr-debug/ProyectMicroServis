package com.yers7.product_service.service;

import java.time.Instant;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import  org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yers7.product_service.dto.ClientResponseDto;
import com.yers7.product_service.dto.ProductRequestDto;
import com.yers7.product_service.dto.ProductResponseDto;
import com.yers7.product_service.dto.ProductUpdate;
import com.yers7.product_service.exception.ProductDuplicateException;
import com.yers7.product_service.exception.ProductNotFoundException;
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

        ProductEntity entity = ProductEntity.builder()
        .createdAt(Instant.now())
        .id(request.id())
        .name(request.name())
        .stock(request.stock())
        .price(request.price())
        .status(ProductStatus.ACTIVE)
        .build();

        productRepository.save(entity);

    
        return new ProductResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getStock()
        );
    }

    @Transactional (readOnly = true)
    public ProductResponseDto getById(Long id){
        ProductEntity entity = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("product not found"));

        return new ProductResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getStock()
        );
    }

     @Transactional (readOnly = true)
    public ClientResponseDto getById(String id){
        Long longId = Long.valueOf(id);
        ProductEntity entity = productRepository.findById(longId)
        .orElseThrow(() -> new ProductNotFoundException("product not found"));

        return new ClientResponseDto(
            entity.getId().toString(),
            entity.getName(),
            entity.getPrice().value(),
            entity.getStock().value()
        );
    }

    public String deleteById(Long id){
        ProductEntity entity = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("product not found"));

        entity.setStatus(ProductStatus.DISCONTINUED);
        productRepository.save(entity);
        return "Product deleted successfully";
    }

    public ProductResponseDto updateById(Long id,ProductUpdate request){

         productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("product not found"));

      ProductEntity update = ProductEntity.builder()
        .name(request.name())
        .status(request.status())
        .stock(request.stock())
        .price(request.price())
        .build();

        productRepository.save(update);

        return new ProductResponseDto(
            update.getId(),
            update.getName(),
            update.getPrice(),
            update.getStock()
        );
    }

    @Transactional (readOnly = true)
    public Page<ProductResponseDto> getAll(int page,int size){
       
        Pageable pageable = PageRequest.of(page, size);
      
        return productRepository.findAll(pageable)
        .map(product -> new ProductResponseDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock()
        ));
    }

}


