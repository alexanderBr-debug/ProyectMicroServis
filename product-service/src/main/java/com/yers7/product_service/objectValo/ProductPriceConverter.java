package com.yers7.product_service.objectValo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true) // autoApply = true hace que JPA lo aplique automáticamente a cualquier campo ProductPrice
public class ProductPriceConverter implements AttributeConverter<ProductPrice, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(ProductPrice attribute) {
        return (attribute != null) ? attribute.value() : null;
    }

    @Override
    public ProductPrice convertToEntityAttribute(BigDecimal dbData) {
        return (dbData != null) ? new ProductPrice(dbData) : null;
    }
}