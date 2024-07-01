package com.example.javaeetest2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public class ExchangeRateReqDTO {
    private final String baseCode;
    private final String targetCode;
    private final BigDecimal rate;
}