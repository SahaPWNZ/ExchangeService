package com.example.javaeetest2.service;

import com.example.javaeetest2.dao.CurrenciesDAO;
import com.example.javaeetest2.dao.ExchangeRatesDAO;
import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.CurrencyResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.dto.ExchangeRateResponseDTO;
import com.example.javaeetest2.exceptions.NotFoundException;
import com.example.javaeetest2.model.ExchangeRate;
import com.example.javaeetest2.utils.MappingUtils;
import com.example.javaeetest2.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataManagementService {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();
    private final ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();

    public CurrencyResponseDTO getCurrencyOnCode(String code) {
        var currency = curDAO.findByCode(code).orElseThrow(() ->
                new NotFoundException("Валюты с данным кодом нет"));
        return MappingUtils.convertToDTO(currency);
    }

    public ArrayList<CurrencyResponseDTO> getCurrenciesList() {
        return curDAO.findAll().
                stream().
                map(MappingUtils::convertToDTO).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public CurrencyResponseDTO insertCurrency(CurrencyRequestDTO curDTO) {
        var currency = curDAO.save(MappingUtils.convertToEntity(curDTO)).orElseThrow();
        return MappingUtils.convertToDTO(currency);
    }

    public ExchangeRateResponseDTO getExchangeRateByCodes(String baseCode, String targetCode) {
        return MappingUtils.convertToDTO(ratesDAO.findByCodes(baseCode, targetCode).orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет")));
    }

    public ExchangeRateResponseDTO updateRate(ExchangeRateReqDTO requestDTO) {
        ratesDAO.findByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode())
                .orElseThrow(() -> new NotFoundException("Курса обмена в бд для данных валют нет"));
        return MappingUtils.convertToDTO(ratesDAO.update(requestDTO));
    }

    public ArrayList<ExchangeRateResponseDTO> getExchangeRatesList() {
        return ratesDAO.findAll().
                stream().
                map(MappingUtils::convertToDTO).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public ExchangeRateResponseDTO insertExchangeRate(ExchangeRateReqDTO requestDTO) {
        ValidationUtils.checkCurrenciesByCodes(requestDTO.getBaseCode(), requestDTO.getTargetCode());
        var exchangeRate = new ExchangeRate(
                curDAO.findByCode(requestDTO.getBaseCode()).get(),
                curDAO.findByCode(requestDTO.getTargetCode()).get(),
                requestDTO.getRate());
        return MappingUtils.convertToDTO(ratesDAO.save(exchangeRate).orElseThrow());
    }
}
