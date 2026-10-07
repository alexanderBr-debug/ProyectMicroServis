package com.yers7.sales_service.application.ports.in;

import java.math.BigDecimal;
import java.util.List;

import com.yers7.sales_service.domain.model.Sale;


public interface CreateSaleUseCase {
    Sale execute(CreateSaleCommand command);

    record CreateSaleCommand(
        String customerId,
        List<SaleItemCommand> items
    ) {
        public CreateSaleCommand {
            if (customerId == null || customerId.isBlank()) {
                throw new IllegalArgumentException("El cliente es requerido");
            }
            if (items == null || items.isEmpty()) {
                throw new IllegalArgumentException("La venta debe tener al menos un ítem");
            }
        }
    }

    record SaleItemCommand(
        String productId,
        int quantity,
        BigDecimal unitPrice,
        String currency
    ) {}
}
