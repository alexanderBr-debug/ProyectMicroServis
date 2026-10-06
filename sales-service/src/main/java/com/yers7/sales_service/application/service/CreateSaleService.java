package com.yers7.sales_service.application.service;

import java.math.BigDecimal;
import java.util.List;

import com.yers7.sales_service.application.ports.in.CreateSaleUseCase;
import com.yers7.sales_service.application.ports.out.ProductClientPort;
import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.exception.InsufficientStockException;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.model.SaleItem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class CreateSaleService implements CreateSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;
    private final SaleEventPublisherPort saleEventPublisherPort;
    private final ProductClientPort  productClientPort;

    @Override
    public Sale execute(String clientId,List<SaleItem> items) {
        
        
        items.forEach(item -> {

            BigDecimal realPrice = productClientPort.getProductPrice(item.getProductId());
            item.setUnitPrice(realPrice);

            Integer stock = productClientPort.getStock(item.getProductId().toString());

            if (stock <= item.getQuantity()) {
                throw new InsufficientStockException("stock insufficient");
            }
        });
       
        Sale newSale = Sale.createPendig(clientId, items);
      
        Sale savedSale = saleRepositoryPort.save(newSale);
    
        saleEventPublisherPort.publishSaleCreated(savedSale);

        return savedSale;
    }
}