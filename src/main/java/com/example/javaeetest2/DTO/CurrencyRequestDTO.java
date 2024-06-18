package com.example.javaeetest2.DTO;

public class CurrencyRequestDTO {
    private final String code;
    private final String fullName;
    private final String sign;

    public CurrencyRequestDTO(String code, String fullName, String sign) {
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSign() {
        return sign;
    }
}
