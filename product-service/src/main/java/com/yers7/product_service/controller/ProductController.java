package com.yers7.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/product")
public class ProductController {
    
    private final ProductService productService;

    @PostMapping ("/create")
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto request){
        ProductResponseDto response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
