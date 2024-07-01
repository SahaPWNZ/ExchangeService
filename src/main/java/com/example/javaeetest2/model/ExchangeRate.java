package com.example.javaeetest2.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@RequiredArgsConstructor
public class ExchangeRate {
    private long id;
    private BigDecimal rate;
    private long baseCurrencyId;
    private long targetCurrencyId;
}
