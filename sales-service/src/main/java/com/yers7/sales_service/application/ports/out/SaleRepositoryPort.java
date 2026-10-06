package com.yers7.sales_service.application.ports.out;

import java.util.Optional;
import java.util.UUID;

import com.yers7.sales_service.domain.model.Sale;

public interface SaleRepositoryPort {
    Sale save(Sale sale);
    Optional<Sale> findById(UUID id);
   
}