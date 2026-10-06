package com.yers7.sales_service.application.ports.out;

import java.math.BigDecimal;

public interface ProductClientPort {
    
    BigDecimal getProductPrice(String productId); 
    Integer getStock(String stock);
}
