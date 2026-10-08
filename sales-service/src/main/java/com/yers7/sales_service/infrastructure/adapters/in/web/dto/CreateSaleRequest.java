package com.yers7.sales_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record CreateSaleRequest(
        @NotBlank(message = "El customerId no puede estar vacío")
        String customerId,

        @NotEmpty(message = "La venta debe contener al menos un producto")
        @Valid
        List<SaleItemRequest> items
) {
    public record SaleItemRequest(
            @NotBlank(message = "El productId es obligatorio")
            String productId,

            @Positive(message = "La cantidad debe ser mayor a cero")
            int quantity,

            @NotNull(message = "El precio unitario es obligatorio")
            @Positive(message = "El precio unitario debe ser positivo")
            BigDecimal unitPrice,

            @NotBlank(message = "La moneda (currency) es obligatoria")
            String currency
    ) {}
}