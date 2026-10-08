package com.yers7.sales_service.infrastructure.adapters.in.web.mapper;

import com.yers7.sales_service.application.ports.in.CreateSaleUseCase.CreateSaleCommand;
import com.yers7.sales_service.application.ports.in.CreateSaleUseCase.SaleItemCommand;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.infrastructure.adapters.in.web.dto.CreateSaleRequest;
import com.yers7.sales_service.infrastructure.adapters.in.web.dto.SaleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleRestMapper {

    // Request HTTP -> Command de Aplicación
    public CreateSaleCommand toCommand(CreateSaleRequest request) {
        List<SaleItemCommand> items = request.items().stream()
                .map(item -> new SaleItemCommand(
                        item.productId(),
                        item.quantity(),
                        item.unitPrice(),
                        item.currency()
                )).toList();

        return new CreateSaleCommand(request.customerId(), items);
    }

    
    public SaleResponse toResponse(Sale sale) {
        return new SaleResponse(
                sale.getId().value().toString(),
                sale.getCustomerId().value(),
                sale.getStatus().name(),
                sale.getTotalAmount().amount(),
                sale.getTotalAmount().currency(),
                sale.getCreatedAt()
        );
    }
}