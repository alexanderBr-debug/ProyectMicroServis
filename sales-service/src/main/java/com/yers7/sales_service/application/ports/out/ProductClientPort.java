package com.yers7.sales_service.application.ports.out;



import com.yers7.sales_service.domain.valueobject.ProductId;

public interface ProductClientPort {
   boolean hasEnoughStock(ProductId productId, int quantity);
}
