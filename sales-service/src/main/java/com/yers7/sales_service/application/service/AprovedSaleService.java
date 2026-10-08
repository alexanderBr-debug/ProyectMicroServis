package com.yers7.sales_service.application.service;

import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.exception.SaleNotFoundException;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.SaleId;
import com.yers7.sales_service.application.ports.in.ApproveSaleUseCase;



import java.util.UUID;


public class AprovedSaleService implements ApproveSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;

    public AprovedSaleService(SaleRepositoryPort saleRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
    }

    @Override
    public void execute(String saleId) {
        SaleId id = new SaleId(UUID.fromString(saleId));

        
        Sale sale = saleRepositoryPort.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Venta no encontrada con ID: " + saleId));

        
        sale.approve();

        
        saleRepositoryPort.save(sale);
    }
}