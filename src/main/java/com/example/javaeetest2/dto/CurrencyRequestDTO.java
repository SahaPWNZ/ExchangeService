package com.example.javaeetest2.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequestDTO {
    private String code;
    private String fullName;
    private String sign;
}
