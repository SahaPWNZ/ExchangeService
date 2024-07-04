package com.example.javaeetest2.utils;

import com.example.javaeetest2.dao.CurrenciesDAO;
import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.exceptions.InvalidParameterException;
import com.example.javaeetest2.exceptions.NotFoundException;

import java.math.BigDecimal;

public class ValidationUtils {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();

    public void isValidCode(String code) {
        if (code != null) {
            if (code.length() != 3) {
                throw new InvalidParameterException("Неверно введён код валюты (не 3 буквы)");
            }
            for (int i = 0; i < code.length(); i++) {
                if (!Character.isLetter(code.charAt(i))) {
                    throw new InvalidParameterException("Неверно введён код валюты (должен состоять только из букв)");
                }
            }
        }
        else{
            throw new InvalidParameterException("Неверно введён код валюты (не 3 буквы)");
        }
    }

    public void isValidCurrencyDTO(CurrencyRequestDTO curDTO) {
        isValidCode(curDTO.getCode());
        if (curDTO.getFullName() == null || curDTO.getFullName().isEmpty()) {
            throw new InvalidParameterException("Отсутствует нужное поле формы (FullName)");
        } else if (curDTO.getSign() == null || curDTO.getSign().isEmpty()) {
            throw new InvalidParameterException("Отсутствует нужное поле формы (Sign)");
        }
    }

    public void checkCurrenciesByCodes(String baseCode, String targetCode) {
        curDAO.findByCode(baseCode).orElseThrow(() -> new NotFoundException("Одной из валют нет в БД"));
        curDAO.findByCode(targetCode).orElseThrow(() -> new NotFoundException("Одной из валют нет в БД"));
    }


    public void checkPathFromServlet(String path) {
        if (path.length() >= 8) {
            throw new InvalidParameterException("Неверно введены коды валюты (больше 6 букв)");
        }
        if (path.length() < 7) {
            throw new InvalidParameterException("Неверно введены коды валют (меньше 6 букв)");
        }
        String currencyBaseCode = path.substring(1, 4);
        String currencyTargetCode = path.substring(4, 7);
        isValidCode(currencyBaseCode);
        isValidCode(currencyTargetCode);
    }

    public void isValidRate(String rateValue) {
        try {
            BigDecimal rate = new BigDecimal(rateValue);
            if (rate.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidParameterException("Неверное поле формы (курс не может быть меньше либо равен нулю)");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Неверное поле формы");
        }
    }
}
