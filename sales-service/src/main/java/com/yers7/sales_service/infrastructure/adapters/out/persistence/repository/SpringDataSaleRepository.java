package com.yers7.sales_service.infrastructure.adapters.out.persistence.repository;

import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface SpringDataSaleRepository extends JpaRepository<SaleEntity, UUID> {
   
}