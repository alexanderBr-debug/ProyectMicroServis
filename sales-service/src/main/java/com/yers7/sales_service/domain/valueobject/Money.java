package com.yers7.sales_service.domain.valueobject;

import java.math.BigDecimal;

import com.yers7.sales_service.domain.exception.AmountCannotBeNegativeException;
import com.yers7.sales_service.domain.exception.CannotAddUpDifferentCurrenciesException;
import com.yers7.sales_service.domain.exception.RequiredCurrencyException;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountCannotBeNegativeException("amount cannot be neggative");
        }
        if (currency == null || currency.isBlank()) {
            throw new RequiredCurrencyException("currency is required");
        }
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency())) {
            throw new CannotAddUpDifferentCurrenciesException("you cannot add up different currencies");
        }
        return new Money(this.amount.add(other.amount()), this.currency);
    }
}

