package com.yers7.sales_service.infrastructure.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.yers7.sales_service.domain.model.SaleItem;
import com.yers7.sales_service.domain.valueobject.Money;
import com.yers7.sales_service.domain.valueobject.ProductId;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleItemEntity;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {Money.class, ProductId.class}
)
public interface SaleItemPersistenceMapper {

    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "productId", source = "productId.id")
    @Mapping(target = "unitPrice", source = "unitPrice.amount")
    @Mapping(target = "currency", source = "unitPrice.currency")
    SaleItemEntity toSaleItemEntity(SaleItem saleItem);

    @Mapping(target = "productId", expression = "java(new ProductId(saleItemEntity.getProductId()))")
    @Mapping(target = "unitPrice", expression = "java(new Money(saleItemEntity.getUnitPrice(), saleItemEntity.getCurrency()))")
    SaleItem toDomain(SaleItemEntity saleItemEntity);
}
