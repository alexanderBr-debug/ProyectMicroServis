package com.yers7.sales_service.application.ports.in;

public interface CancelSaleUseCase {
    void execute(String saleId, String reason);
}