package com.example.javaeetest2.Service;

import com.example.javaeetest2.DAO.CurrenciesDAO;
import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.example.javaeetest2.DTO.CurrencyRequestDTO;
import com.example.javaeetest2.Exceptions.InvalidDataException;
import com.example.javaeetest2.Exceptions.NotFoundException;

import java.math.BigDecimal;

public class ValidationService {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();
    private final ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();

    public void isValidCode(String code) {
        if (code.length() != 3) {
            throw new InvalidDataException("Неверно введён код валюты (не 3 буквы)");
        }
        for (int i = 0; i < code.length(); i++) {
            if (!Character.isLetter(code.charAt(i))) {
                throw new InvalidDataException("Неверно введён код валюты (должен состоять только из букв)");
            }
        }
    }

    public void isValidCurrencyDTO(CurrencyRequestDTO curDTO) {
        isValidCode(curDTO.getCode());
        if (curDTO.getFullName() == null || curDTO.getFullName().isEmpty() || curDTO.getSign() == null || curDTO.getSign().isEmpty()) {
            throw new InvalidDataException("Отсутствует нужное поле формы");
        }
    }

    public void checkCurrenciesByCodes(String baseCode, String targetCode){
        curDAO.getCurrencyOnCode(baseCode).orElseThrow(() -> new NotFoundException("Одного из курса валют нет"));
        curDAO.getCurrencyOnCode(targetCode).orElseThrow(() -> new NotFoundException("Одного из курса валют нет"));
    }


    public void checkPathFromServlet(String path) {
        if (path.length() >= 8) {
            throw new InvalidDataException("Неверно введены коды валюты (больше 6 букв)");
        }
        if (path.length() < 7) {
            throw new InvalidDataException("Неверно введены коды валют (меньше 6 букв)");
        }
        String currencyBaseCode = path.substring(1, 4);
        String currencyTargetCode = path.substring(4, 7);
        isValidCode(currencyBaseCode);
        isValidCode(currencyTargetCode);
    }
    public void isValidRate(String rateValue) {
        try {
            BigDecimal.valueOf(Double.parseDouble(rateValue));
        } catch (Exception e) {
            throw new InvalidDataException("Неверное поле формы");
        }
    }
    public boolean isExchangeRateInDB(String baseCode, String targetCode){
        return ratesDAO.getExchangeRateOnCodes(baseCode, targetCode).orElse(null) != null;
    }





}
