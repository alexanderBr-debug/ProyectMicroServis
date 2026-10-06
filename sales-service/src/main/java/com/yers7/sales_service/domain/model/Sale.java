package com.yers7.sales_service.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

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
public class Sale {

    private UUID id;
    private String clientId;
    private List<SaleItem> items;
    private BigDecimal totalAmount;
    private SaleStatus status;
    private Instant createdAt;

    public static Sale createPendig(String clientId,List<SaleItem> items){

        return Sale.builder()
        .id(UUID.randomUUID())
        .clientId(clientId)
        .createdAt(Instant.now())
        .items(items)
        .status(SaleStatus.PENDING)
        .totalAmount(calculateTotal(items))
        .build();
    }

    public static BigDecimal calculateTotal(List<SaleItem> items){
        return items.stream()
        .map(SaleItem::getSubTotal)
        .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
