package com.example.javaeetest2.Service;

import com.example.javaeetest2.DAO.CurrenciesDAO;
import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.example.javaeetest2.DTO.CurrencyRequestDTO;
import com.example.javaeetest2.DTO.CurrencyResponseDTO;
import com.example.javaeetest2.DTO.ExchangeRateRequestDTO;
import com.example.javaeetest2.DTO.ExchangeRateResponseDTO;
import com.example.javaeetest2.Exceptions.NotFoundException;

import java.util.ArrayList;

public class DataManagementService {
    private static final CurrenciesDAO curDAO = new CurrenciesDAO();
    public static final ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();
    private final ValidationService validationService = new ValidationService();

    public static CurrencyResponseDTO getCurrencyOnCode(String code) {
        return curDAO.getCurrencyOnCode(code).orElseThrow(() ->
                new NotFoundException("Валюты с данным кодом нет"));
    }

    public static ArrayList<CurrencyResponseDTO> getCurrenciesList() {
        return curDAO.getCurrenciesList();
    }

    public CurrencyResponseDTO insertCurrency(CurrencyRequestDTO curDTO) {
        validationService.checkCurrencyByCode(curDTO.getCode());
        return curDAO.insertCurrency(curDTO).orElseThrow();
    }

    public ExchangeRateResponseDTO getExchangeRateByCodes(String baseCode, String targetCode) {
        validationService.checkCurrenciesByCodes(baseCode, targetCode);
        return ratesDAO.getExchangeRateOnCodes(baseCode, targetCode).orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
    }

    public ExchangeRateResponseDTO updateRate(ExchangeRateRequestDTO requestDTO) {
        validationService.checkCurrenciesByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode());
        ratesDAO.getExchangeRateOnCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode())
                .orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
        return ratesDAO.updateRate(requestDTO);
    }

    public ArrayList<ExchangeRateResponseDTO>
//
//    public static ExchangeRateResponseDTO insertExchangeRate(ExchangeRateRequestDTO requestDTO) {
//        return ratesDAO.insertExchangeRate(requestDTO).orElseThrow();
//    }
}
