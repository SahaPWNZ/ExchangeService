package com.example.javaeetest2.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public class ExchangeRateWithAmountResponceDTO{
    private final CurrencyResponseDTO CurrencyBase;
    private final CurrencyResponseDTO CurrencyTarget;
    private final BigDecimal rate;
    private final BigDecimal amount;
    private final BigDecimal convertedAmount;
}
