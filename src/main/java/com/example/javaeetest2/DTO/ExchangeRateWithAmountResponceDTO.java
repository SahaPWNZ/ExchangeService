package com.example.javaeetest2.DTO;

import java.math.BigDecimal;

public class ExchangeRateWithAmountResponceDTO{
    private final CurrencyResponseDTO CurrencyBase;
    private final CurrencyResponseDTO CurrencyTarget;
    private final BigDecimal rate;
    private final BigDecimal amount;
    private final BigDecimal convertedAmount;

    public CurrencyResponseDTO getCurrencyBase() {
        return CurrencyBase;
    }

    public CurrencyResponseDTO getCurrencyTarget() {
        return CurrencyTarget;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public ExchangeRateWithAmountResponceDTO(CurrencyResponseDTO currencyBase, CurrencyResponseDTO currencyTarget, BigDecimal rate, BigDecimal amount, BigDecimal convertedAmount) {
        CurrencyBase = currencyBase;
        CurrencyTarget = currencyTarget;
        this.rate = rate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }
}
