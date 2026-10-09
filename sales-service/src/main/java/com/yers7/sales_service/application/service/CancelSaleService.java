package com.yers7.sales_service.application.service;

import com.yers7.sales_service.application.ports.in.CancelSaleUseCase;
import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.exception.SaleNotFoundException;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.SaleId;

import java.util.UUID;

public class CancelSaleService implements CancelSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;
    private final SaleEventPublisherPort saleEventPublisherPort;

    public CancelSaleService(
            SaleRepositoryPort saleRepositoryPort,
            SaleEventPublisherPort saleEventPublisherPort
    ) {
        this.saleRepositoryPort = saleRepositoryPort;
        this.saleEventPublisherPort = saleEventPublisherPort;
    }

    @Override
    public void execute(String saleId, String reason) {
        SaleId id = new SaleId(UUID.fromString(saleId));

        
        Sale sale = saleRepositoryPort.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Venta no encontrada con ID: " + saleId));

        
        sale.cancel(reason);

       
        Sale updatedSale = saleRepositoryPort.save(sale);

        
        saleEventPublisherPort.publishSaleCancelled(updatedSale, reason);
    }
}