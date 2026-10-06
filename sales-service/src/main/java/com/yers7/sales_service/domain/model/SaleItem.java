package com.yers7.sales_service.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class SaleItem {

    private String productId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public BigDecimal getSubTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
