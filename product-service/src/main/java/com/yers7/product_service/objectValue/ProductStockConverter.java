package com.yers7.product_service.objectValue;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductStockConverter implements AttributeConverter<ProductStock, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStock attribute) {
        return (attribute != null) ? attribute.value() : null;
    }

    @Override
    public ProductStock convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? new ProductStock(dbData) : null;
    }
}