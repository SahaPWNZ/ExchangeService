package com.example.javaeetest2.DTO;

import java.math.BigDecimal;

public class ExchangeRateWithAmountRequestDTO {
    private final String baseCurrencyCode;
    private final String targetCurrencyCode;
    private final BigDecimal amount;


    public ExchangeRateWithAmountRequestDTO(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.amount = amount;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
