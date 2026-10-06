package com.yers7.sales_service.application.ports.in;

import java.util.List;

import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.model.SaleItem;

public interface CreateSaleUseCase {
    Sale execute(String clientId, List<SaleItem> items);
}
