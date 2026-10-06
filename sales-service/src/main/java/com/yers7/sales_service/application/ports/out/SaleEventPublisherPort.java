package com.yers7.sales_service.application.ports.out;

import com.yers7.sales_service.domain.model.Sale;

public interface SaleEventPublisherPort {
    void publishSaleCreated(Sale sale);
    void publishSaleCancelled(Sale sale, String reason);
}
