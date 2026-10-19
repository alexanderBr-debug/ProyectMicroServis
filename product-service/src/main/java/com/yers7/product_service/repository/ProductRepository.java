package com.yers7.product_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yers7.product_service.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
  
}
