package com.yers7.sales_service.application.ports.out;

import java.util.Optional;


import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.SaleId;


public interface SaleRepositoryPort {
    Sale save(Sale sale);
    Optional<Sale> findById(SaleId id);
}