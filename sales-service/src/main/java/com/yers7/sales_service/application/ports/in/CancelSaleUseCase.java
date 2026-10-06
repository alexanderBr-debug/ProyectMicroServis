package com.yers7.sales_service.application.ports.in;

import java.util.UUID;

public interface CancelSaleUseCase {
    void execute(UUID saleId, String reason);
}