package com.example.javaeetest2.service;

import com.example.javaeetest2.dao.CurrenciesDAO;
import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.CurrencyResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.exceptions.NotFoundException;
import com.example.javaeetest2.utils.MappingUtils;

import java.math.BigDecimal;

public class DataManagementService {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();
//    private final ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();
    private final ValidationService validationService = new ValidationService();

    public ExchangeRateReqDTO getDtoByRequest(String baseCode, String targetCode, BigDecimal value) {
        return new ExchangeRateReqDTO(baseCode, targetCode, value);
    }

    public CurrencyResponseDTO getCurrencyOnCode(String code) {
        var currency = curDAO.findByCode(code).orElseThrow(() ->
                new NotFoundException("Валюты с данным кодом нет"));
        return MappingUtils.convertToDTO(currency);
    }

//    public ArrayList<CurrencyResponseDTO> getCurrenciesList() {
//        return curDAO.findAll();
//    }

    public CurrencyResponseDTO insertCurrency(CurrencyRequestDTO curDTO) {
        var currency = curDAO.save(MappingUtils.convertToEntity(curDTO)).orElseThrow();
        return MappingUtils.convertToDTO(currency);
    }

//    public ExchangeRateResponseDTO getExchangeRateByCodes(String baseCode, String targetCode) {
//        return ratesDAO.findByCodes(baseCode, targetCode).orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
//    }
//
//    public ExchangeRateResponseDTO updateRate(ExchangeRateReqDTO requestDTO) {
//        ratesDAO.findByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode())
//                .orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
//        return ratesDAO.update(requestDTO);
//    }

//    public ArrayList<ExchangeRateResponseDTO> getExchangeRatesList() {
//        return ratesDAO.findAll();
//    }
//
//    public ExchangeRateResponseDTO insertExchangeRate(ExchangeRateReqDTO requestDTO) {
//        validationService.checkCurrenciesByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode());
//            return ratesDAO.save(requestDTO).orElseThrow();
//    }
}
