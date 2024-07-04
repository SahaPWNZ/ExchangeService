package com.example.javaeetest2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRateWithAmountResponceDTO {
    private CurrencyResponseDTO CurrencyBase;
    private CurrencyResponseDTO CurrencyTarget;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
}
