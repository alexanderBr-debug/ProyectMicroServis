package com.yers7.sales_service.application.service;

import java.util.List;
import java.util.UUID;

import com.yers7.sales_service.application.ports.in.CreateSaleUseCase;

import com.yers7.sales_service.application.ports.out.ProductClientPort;
import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.exception.DomainException;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.model.SaleItem;
import com.yers7.sales_service.domain.valueobject.CustomerId;
import com.yers7.sales_service.domain.valueobject.Money;
import com.yers7.sales_service.domain.valueobject.ProductId;
import com.yers7.sales_service.domain.valueobject.SaleId;

public class CreateSaleService implements CreateSaleUseCase {
    
    private final SaleRepositoryPort saleRepositoryPort;
    private final SaleEventPublisherPort saleEventPublisherPort;
    private final ProductClientPort productClientPort;

    public CreateSaleService(
            SaleRepositoryPort saleRepositoryPort,
            SaleEventPublisherPort saleEventPublisherPort,
            ProductClientPort productClientPort
    ) {
        this.saleRepositoryPort = saleRepositoryPort;
        this.saleEventPublisherPort = saleEventPublisherPort;
        this.productClientPort = productClientPort;
    }

    @Override
    public Sale execute(CreateSaleCommand command) {
        SaleId saleId = new SaleId(UUID.randomUUID());
        CustomerId customerId = new CustomerId(command.customerId());

      
        List<SaleItem> items = command.items().stream()
                .map(item -> {
                    ProductId productId = new ProductId(UUID.fromString(item.productId()));
                    
                    boolean stockAvailable = productClientPort.hasEnoughStock(productId, item.quantity());
                    if (!stockAvailable) {
                        throw new DomainException("Stock insuficiente para el producto: " + item.productId());
                    }

                    return new SaleItem(
                            productId,
                            item.quantity(),
                            new Money(item.unitPrice(), item.currency())
                    );
                })
                .toList();

  
        Sale sale = Sale.createPending(saleId, customerId, items);

        Sale savedSale = saleRepositoryPort.save(sale);


        saleEventPublisherPort.publishSaleCreated(savedSale);

        return savedSale;
    }
}

