package com.yers7.product_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yers7.product_service.dto.ClientResponseDto;
import com.yers7.product_service.dto.ProductRequestDto;
import com.yers7.product_service.dto.ProductResponseDto;
import com.yers7.product_service.dto.ProductUpdate;
import com.yers7.product_service.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping ("/{productId}")
    public ResponseEntity<ClientResponseDto> getProductId(@PathVariable String productId){
        ClientResponseDto response = productService.getById(productId);
        return ResponseEntity.ok(response);
    }


    @PostMapping ("/create")
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto request){
        ProductResponseDto response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/getById/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id){
        ProductResponseDto response = productService.getById(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping ("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        String response = productService.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping ("/updateById/{id}")
    public ResponseEntity<ProductResponseDto> updateById(@PathVariable Long id, @Valid @RequestBody ProductUpdate request){
        ProductResponseDto response = productService.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping ("/getAll")
    public ResponseEntity<Page<ProductResponseDto>> getAll(
         @RequestParam (defaultValue = "0") int page,
        @RequestParam (defaultValue = "10") int size){

        Page<ProductResponseDto> response = productService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



   

}