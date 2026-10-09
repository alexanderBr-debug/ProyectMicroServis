package com.yers7.sales_service.infrastructure.adapters.out.messaging;

import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.infrastructure.adapters.out.messaging.event.SaleCancelledEvent;
import com.yers7.sales_service.infrastructure.adapters.out.messaging.event.SaleCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaSaleEventPublisherAdapter implements SaleEventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC_SALE_CREATED = "sale-created-events";
    private static final String TOPIC_SALE_CANCELLED = "sale-cancelled-events";

    @Override
    public void publishSaleCreated(Sale sale) {
        List<SaleCreatedEvent.SaleItemEvent> items = sale.getItems().stream()
                .map(item -> new SaleCreatedEvent.SaleItemEvent(
                        item.getProductId().toString(),
                        item.getQuantity()
                )).toList();

        String saleId = sale.getId().value().toString();

        kafkaTemplate.send(
                TOPIC_SALE_CREATED, 
                saleId, // Key para garantizar orden en la partición
                new SaleCreatedEvent(
                        UUID.randomUUID().toString(),
                        saleId,
                        sale.getCustomerId().value(),
                        sale.getTotalAmount().amount(),
                        Instant.now(),
                        items
                )
        );
    }

    @Override
    public void publishSaleCancelled(Sale sale, String reason) {
        String saleId = sale.getId().value().toString();

        kafkaTemplate.send(
                TOPIC_SALE_CANCELLED, 
                saleId, 
                new SaleCancelledEvent(
                        UUID.randomUUID().toString(),
                        saleId,
                        reason,
                        Instant.now()
                )
        );
    }
}