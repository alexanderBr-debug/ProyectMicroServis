package com.yers7.sales_service.infrastructure.adapters.in.messaging;

import com.yers7.sales_service.application.ports.in.ApproveSaleUseCase;
import com.yers7.sales_service.application.ports.in.CancelSaleUseCase;
import com.yers7.sales_service.domain.exception.DomainException;
import com.yers7.sales_service.domain.exception.SaleNotFoundException;
import com.yers7.sales_service.infrastructure.adapters.in.messaging.event.PaymentFailedEvent;
import com.yers7.sales_service.infrastructure.adapters.in.messaging.event.StockDeductedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StockDeductedConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockDeductedConsumer.class);
    
    private final ApproveSaleUseCase approveSaleUseCase;
    private final CancelSaleUseCase cancelSaleUseCase;

    public StockDeductedConsumer(ApproveSaleUseCase approveSaleUseCase, CancelSaleUseCase cancelSaleUseCase) {
        this.approveSaleUseCase = approveSaleUseCase;
        this.cancelSaleUseCase = cancelSaleUseCase;
    }

    @KafkaListener(topics = "stock-deducted-events", groupId = "sales-service-group")
    public void consumeStockDeducted(StockDeductedEvent event) {
        log.info("Recibido evento StockDeducted [eventId: {}] para la venta: {}", event.eventId(), event.saleId());
        
        try {
           
            approveSaleUseCase.execute(event.saleId());
            log.info("ÉXITO: Venta {} aprobada correctamente.", event.saleId());
            
        } catch (DomainException | SaleNotFoundException e) {
          
            log.warn("Evento de stock ignorado o ya procesado para venta {}. Razón: {}", event.saleId(), e.getMessage());
        } catch (Exception e) {
            
            log.error("Fallo inesperado procesando stock para venta {}. Se reintentará.", event.saleId(), e);
            throw e; 
        }
    }

    @KafkaListener(topics = "payment-failed-events", groupId = "sales-service-group")
    public void consumePaymentFailed(PaymentFailedEvent event) {
        log.warn("FALLO: Evento PaymentFailed recibido para la venta: {}. Razón: {}. Ejecutando compensación...", 
                event.saleId(), event.reason());
        
        try {
            cancelSaleUseCase.execute(event.saleId(), "Fallo en el pago: " + event.reason());
            log.info("SAGA: Compensación exitosa, venta {} cancelada.", event.saleId());
            
        } catch (DomainException | SaleNotFoundException e) {
            log.warn("Evento de compensación ignorado para venta {}. Razón: {}", event.saleId(), e.getMessage());
        } catch (Exception e) {
            log.error("Fallo inesperado cancelando venta {}. Se reintentará.", event.saleId(), e);
            throw e;
        }
    }
}