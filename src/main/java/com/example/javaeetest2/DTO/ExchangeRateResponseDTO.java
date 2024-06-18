package com.example.javaeetest2.DTO;

import java.math.BigDecimal;

public class ExchangeRateResponseDTO {
private final int Id;
private final CurrencyResponseDTO CurrencyBase;
private final CurrencyResponseDTO CurrencyTarget;
private final BigDecimal rate;

    public int getId() {
        return Id;
    }

    public CurrencyResponseDTO getCurrencyBase() {
        return CurrencyBase;
    }

    public CurrencyResponseDTO getCurrencyTarget() {
        return CurrencyTarget;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExchangeRateResponseDTO(int id, CurrencyResponseDTO currencyBase, CurrencyResponseDTO currencyTarget, BigDecimal rate) {
        Id = id;
        CurrencyBase = currencyBase;
        CurrencyTarget = currencyTarget;
        this.rate = rate;
    }
}
