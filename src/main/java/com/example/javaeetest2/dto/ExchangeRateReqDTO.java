package com.example.javaeetest2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRateReqDTO {
    private String baseCode;
    private String targetCode;
    private BigDecimal rate;
}