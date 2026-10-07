package com.yers7.sales_service.domain.model;

import java.math.BigDecimal;

import com.yers7.sales_service.domain.exception.AmountCannotBeNegativeException;
import com.yers7.sales_service.domain.valueobject.Money;
import com.yers7.sales_service.domain.valueobject.ProductId;

import lombok.Getter;

@Getter 
public class SaleItem {

    private final ProductId productId;
    private final int quantity;
    private final Money unitPrice;

    public SaleItem(ProductId productId, int quantity, Money unitPrice) {
        if (quantity <= 0) throw new AmountCannotBeNegativeException("La cantidad debe ser mayor a cero");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Money calculateSubTotal() {
        return new Money(
            this.unitPrice.amount().multiply(BigDecimal.valueOf(quantity)),
            this.unitPrice.currency()
        );
    }


}