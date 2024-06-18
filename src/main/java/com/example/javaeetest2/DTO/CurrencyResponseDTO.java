package com.example.javaeetest2.DTO;

public class CurrencyResponseDTO {
    private final int id;
    private final String code;
    private final String fullName;
    private final String sign;

    public CurrencyResponseDTO(int id, String code, String fullName, String sign) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    public int getId() {
        return id;
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
