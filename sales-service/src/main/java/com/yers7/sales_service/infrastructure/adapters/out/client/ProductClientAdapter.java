package com.yers7.sales_service.infrastructure.adapters.out.client;


import com.yers7.sales_service.application.ports.out.ProductClientPort;
import com.yers7.sales_service.domain.valueobject.ProductId;
import com.yers7.sales_service.infrastructure.adapters.out.client.dto.ProductResponseDto;
import com.yers7.sales_service.infrastructure.adapters.out.client.feign.ProductFeignClient;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductClientAdapter implements ProductClientPort  {

    private static final Logger log = LoggerFactory.getLogger(ProductClientAdapter.class);
    
    private final ProductFeignClient feignClient;

    public ProductClientAdapter(ProductFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public boolean hasEnoughStock(ProductId productId, int quantity) {
        log.info("Consultando disponibilidad para producto {} en Product Service", productId);
        
        try {
            ProductResponseDto product = feignClient.getProductById(productId.toString());

            if (product.availableStock() < quantity) {
                log.warn("Stock insuficiente para el producto {}. Solicitado: {}, Disponible: {}",
                        productId, quantity, product.availableStock());
                return false; 
            }
            return true;

        } catch (FeignException.NotFound e) {
            // Error 404: El producto no existe en el otro microservicio
            log.error("El producto {} no existe en el catálogo.", productId);
            return false;
            
        } catch (FeignException e) {
           
            log.error("Fallo de comunicación con Product Service al consultar el producto {}", productId, e);
           
            throw new RuntimeException("Error temporal comunicando con el catálogo de productos", e);
        }
    }
}