package com.yers7.sales_service.infrastructure.adapters.out.persistence.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.yers7.sales_service.domain.enums.SaleStatus;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.CustomerId;
import com.yers7.sales_service.domain.valueobject.Money;
import com.yers7.sales_service.domain.valueobject.SaleId;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleEntity;



    @Mapper(
    componentModel = "spring", 
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {SaleItemPersistenceMapper.class}, 
    imports = {SaleId.class, CustomerId.class, Money.class, SaleStatus.class} 
)
public interface SalePersistenceMapper {

    // 1. Dominio -> Entidad JPA
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "customerId", source = "customerId.value")
    @Mapping(target = "totalAmount", source = "totalAmount.amount")
    @Mapping(target = "currency", source = "totalAmount.currency")
    SaleEntity toEntity(Sale sale);

    // Método mágico de MapStruct para mantener la relación bidireccional de JPA
    @AfterMapping
    default void linkItems(@MappingTarget SaleEntity entity) {
        if (entity.getItems() != null) {
            entity.getItems().forEach(item -> item.setSale(entity));
        }
    }

    // 2. Entidad JPA -> Dominio
    @Mapping(target = "id", expression = "java(new SaleId(entity.getId()))")
    @Mapping(target = "customerId", expression = "java(new CustomerId(entity.getCustomerId()))")
    @Mapping(target = "status", expression = "java(SaleStatus.valueOf(entity.getStatus()))")
    @Mapping(target = "totalAmount", expression = "java(new Money(entity.getTotalAmount(), entity.getCurrency()))")
    Sale toDomain(SaleEntity entity);
}
