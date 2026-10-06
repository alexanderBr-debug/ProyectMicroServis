package com.yers7.sales_service.application.service;

import java.util.UUID;

import com.yers7.sales_service.application.ports.in.CancelSaleUseCase;
import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.exception.SaleNotFoundException;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.model.SaleStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class CancelSaleService implements CancelSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;
    private final SaleEventPublisherPort saleEventPublisherPort;

    @Override
    public void  execute(UUID saleId, String reason) {
       
        Sale sale = saleRepositoryPort.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));

        
        sale.setStatus(SaleStatus.CANCELLED);

        saleRepositoryPort.save(sale);

        saleEventPublisherPort.publishSaleCancelled(sale, reason);
    }
}
