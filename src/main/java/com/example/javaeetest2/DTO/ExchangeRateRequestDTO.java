package com.example.javaeetest2.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public class ExchangeRateRequestDTO {
    private final String baseCode;
    private final String targetCode;
    private final BigDecimal rate;
}
