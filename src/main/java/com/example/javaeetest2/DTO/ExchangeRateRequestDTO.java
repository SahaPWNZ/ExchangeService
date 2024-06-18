package com.example.javaeetest2.DTO;

import java.math.BigDecimal;

public class ExchangeRateRequestDTO {
    private final String baseCode;
    private final String targetCode;
    private final BigDecimal rate;


    public ExchangeRateRequestDTO(String baseCode, String targetCode, BigDecimal rate) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.rate = rate;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
