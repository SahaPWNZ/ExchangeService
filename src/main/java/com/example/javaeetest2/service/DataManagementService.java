package com.example.javaeetest2.service;

import com.example.javaeetest2.dao.CurrenciesDAO;
import com.example.javaeetest2.dao.ExchangeRatesDAO;
import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.CurrencyResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.dto.ExchangeRateResponseDTO;
import com.example.javaeetest2.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DataManagementService {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();
    private final ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();
    private final ValidationService validationService = new ValidationService();

    public ExchangeRateReqDTO getDtoByRequest(String baseCode, String targetCode, BigDecimal value) {
        return new ExchangeRateReqDTO(baseCode, targetCode, value);
    }

    public CurrencyResponseDTO getCurrencyOnCode(String code) {
        return curDAO.findByCode(code).orElseThrow(() ->
                new NotFoundException("Валюты с данным кодом нет"));
    }

    public ArrayList<CurrencyResponseDTO> getCurrenciesList() {
        return curDAO.findAll();
    }

    public CurrencyResponseDTO insertCurrency(CurrencyRequestDTO curDTO) {
        return curDAO.save(curDTO).orElseThrow();
    }

    public ExchangeRateResponseDTO getExchangeRateByCodes(String baseCode, String targetCode) {
        return ratesDAO.findByCodes(baseCode, targetCode).orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
    }

    public ExchangeRateResponseDTO updateRate(ExchangeRateReqDTO requestDTO) {
        ratesDAO.findByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode())
                .orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
        return ratesDAO.update(requestDTO);
    }

    public ArrayList<ExchangeRateResponseDTO> getExchangeRatesList() {
        return ratesDAO.findAll();
    }

    public ExchangeRateResponseDTO insertExchangeRate(ExchangeRateReqDTO requestDTO) {
        validationService.checkCurrenciesByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode());
            return ratesDAO.save(requestDTO).orElseThrow();
    }
}
