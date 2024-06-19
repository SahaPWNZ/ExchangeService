package com.example.javaeetest2.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrencyRequestDTO {
    private final String code;
    private final String fullName;
    private final String sign;
}
