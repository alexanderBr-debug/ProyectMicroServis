package com.yers7.product_service.messaging.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yers7.product_service.exception.ProductNotFoundException;
import com.yers7.product_service.exception.StatusConflictException;

import com.yers7.product_service.messaging.dto.SaleCreatedEvent;
import com.yers7.product_service.model.ProductEntity;

import com.yers7.product_service.repository.ProductRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 

public class ProductServiceConsumer { 

    private final ProductRepository productRepository;

    public ProductServiceConsumer (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional 
    public void processStockReservation(SaleCreatedEvent event) {
        log.info("Procesando reserva de stock para la venta ID: {}", event.saleId());

        for (SaleCreatedEvent.SaleItemEvent item : event.items()) {
           
            Long productId = Long.valueOf(item.productId().toString());          
            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + productId));
     
           boolean reservaExitosa = product.verificarStatus(item.quantity());

           if (!reservaExitosa) {
                throw new StatusConflictException("error de estatos");
           }

            productRepository.save(product);
            log.info("Stock actualizado correctamente para el producto ID: {}", productId);
        }
    }

    
}
