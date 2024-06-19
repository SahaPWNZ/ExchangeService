package com.example.javaeetest2.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public class ExchangeRateResponseDTO {
private final int Id;
private final CurrencyResponseDTO CurrencyBase;
private final CurrencyResponseDTO CurrencyTarget;
private final BigDecimal rate;
}
