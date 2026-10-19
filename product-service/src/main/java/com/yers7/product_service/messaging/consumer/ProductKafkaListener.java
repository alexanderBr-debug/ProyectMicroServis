package com.yers7.product_service.messaging.consumer;


import org.springframework.retry.annotation.Backoff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;

import org.springframework.stereotype.Component;

import com.yers7.product_service.messaging.dto.SaleCreatedEvent;

import com.yers7.product_service.messaging.service.ProductServiceConsumer;


import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component 
 
public class ProductKafkaListener {

   
    private final ProductServiceConsumer productServiceConsumer;

    public ProductKafkaListener(ProductServiceConsumer productServiceConsumer){
        this.productServiceConsumer = productServiceConsumer;
      
    }

    @RetryableTopic (
            attempts = "3",
            backoff  = @Backoff (delay = 3000, multiplier = 2.0)
    )
    @KafkaListener (topics = "sale-created-events")
    public void handleSaleCreated(SaleCreatedEvent event) {
        try {
        log.info("Evento 'sale-created-events' recibido para la venta ID: {}", event.saleId());
        
        productServiceConsumer.processStockReservation(event);
        
        log.info("Stock procesado y reservado correctamente para la venta: {}", event.saleId());
    } catch(Exception e) {
        log.error("error : {}", e.getMessage());
        throw e;
    }
}
}
    