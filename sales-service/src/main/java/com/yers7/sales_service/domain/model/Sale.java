package com.yers7.sales_service.domain.model;



import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import com.yers7.sales_service.domain.enums.SaleStatus;
import com.yers7.sales_service.domain.exception.DomainException;
import com.yers7.sales_service.domain.valueobject.CustomerId;
import com.yers7.sales_service.domain.valueobject.Money;
import com.yers7.sales_service.domain.valueobject.SaleId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Sale {
    private final SaleId id;
    private final CustomerId customerId;
    private final List<SaleItem> items;
    private final Money totalAmount;
    private final Instant createdAt;
    
    private SaleStatus status;
    private String cancellationReason;

    // Constructor privado. Forzamos el uso de un Factory Method.
    private Sale(SaleId id, CustomerId customerId, List<SaleItem> items, Money totalAmount, Instant createdAt, SaleStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.status = status;
    }

   
    public static Sale createPending(SaleId id, CustomerId customerId, List<SaleItem> items) {
        if (items == null || items.isEmpty()) {
            throw new DomainException("it must have at least one product");
        }


        Money total = items.stream()
                .map(SaleItem::calculateSubTotal)
                .reduce(new Money(BigDecimal.ZERO, "USD"), Money::add);

        return new Sale(id, customerId, items, total, Instant.now(), SaleStatus.PENDING);
    }



    public void approve() {
        if (this.status != SaleStatus.PENDING) {
            throw new DomainException("only sales in pending status can be approved. Current status: " + this.status);
        }
        this.status = SaleStatus.APPROVED;
    }

    public void cancel(String reason) {
        if (this.status == SaleStatus.CANCELLED) {
            throw new DomainException("the sale has already been cancelladA");
        }
        if (this.status == SaleStatus.APPROVED) {
            throw new DomainException("A sale that has already been approved and shipped cannot be cancelled");
        }
        this.status = SaleStatus.CANCELLED;
        this.cancellationReason = reason;
    }


    public List<SaleItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    
   
}
