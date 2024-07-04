package com.example.javaeetest2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
public class ExchangeRateResponseDTO {
    private Long Id;
    private CurrencyResponseDTO CurrencyBase;
    private CurrencyResponseDTO CurrencyTarget;
    private BigDecimal rate;
}
