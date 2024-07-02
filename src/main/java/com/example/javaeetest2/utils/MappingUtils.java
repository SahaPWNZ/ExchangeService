package com.example.javaeetest2.utils;

import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.CurrencyResponseDTO;
import com.example.javaeetest2.model.Currency;
import org.modelmapper.ModelMapper;

public class MappingUtils {
private static final ModelMapper MODEL_MAPPER;
static {
    MODEL_MAPPER = new ModelMapper();
}
public static Currency convertToEntity (CurrencyRequestDTO currencyRequestDTO){
    return MODEL_MAPPER.map(currencyRequestDTO, Currency.class);
}
    public static CurrencyResponseDTO convertToDTO (Currency currency){
return MODEL_MAPPER.map(currency, CurrencyResponseDTO.class);
    }
}
