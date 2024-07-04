package com.example.javaeetest2.service;

import com.example.javaeetest2.dao.CurrenciesDAO;
import com.example.javaeetest2.dao.ExchangeRatesDAO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.dto.ExchangeRateWithAmountResponceDTO;
import com.example.javaeetest2.exceptions.NotFoundException;
import com.example.javaeetest2.model.ExchangeRate;
import com.example.javaeetest2.utils.MappingUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeService {
    private final CurrenciesDAO curDAO = new CurrenciesDAO();
    private final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();

    private boolean isExchangeRateInDB(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRatesDAO.isExchangeRateInDB(baseCurrencyCode,targetCurrencyCode);
    }

    private boolean isBackExchangeRateInDB(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRatesDAO.isExchangeRateInDB(targetCurrencyCode, baseCurrencyCode);
    }

    private boolean isUSDExchangeRate(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRatesDAO.isExchangeRateInDB("USD", baseCurrencyCode) && exchangeRatesDAO.isExchangeRateInDB("USD", targetCurrencyCode);
    }

    private ExchangeRateWithAmountResponceDTO getResponceDTOonRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        BigDecimal convertedAmount = amount.multiply(exchangeRatesDAO.getRateOnCodes(baseCurrencyCode, targetCurrencyCode)).setScale(2, RoundingMode.HALF_UP);
        ExchangeRate exchangeRate = exchangeRatesDAO.findByCodes(baseCurrencyCode, targetCurrencyCode).orElseThrow();

        return new ExchangeRateWithAmountResponceDTO(
                MappingUtils.convertToDTO(exchangeRate.getBaseCurrency()),
                MappingUtils.convertToDTO(exchangeRate.getTargetCurrency()),
                exchangeRate.getRate(),
                amount,
                convertedAmount);
    }

    private ExchangeRateWithAmountResponceDTO getResponceDTOonBackRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        BigDecimal convertedAmount = amount.divide(exchangeRatesDAO.getRateOnCodes(targetCurrencyCode, baseCurrencyCode), 2, RoundingMode.HALF_UP);

        return new ExchangeRateWithAmountResponceDTO(
                MappingUtils.convertToDTO(curDAO.findByCode(baseCurrencyCode).get()),
                MappingUtils.convertToDTO(curDAO.findByCode(targetCurrencyCode).get()),
                convertedAmount.divide(amount, 6, RoundingMode.HALF_UP),
                amount,
                convertedAmount);
    }

    public ExchangeRateWithAmountResponceDTO getResponceDTOonUSDRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        BigDecimal rateBaseOnTarget =
                exchangeRatesDAO.getRateOnCodes("USD", targetCurrencyCode)
                        .divide(exchangeRatesDAO.getRateOnCodes("USD", baseCurrencyCode), 2, RoundingMode.HALF_UP);
        BigDecimal convertedAmount = amount.multiply(rateBaseOnTarget).setScale(2, RoundingMode.HALF_UP);

        return new ExchangeRateWithAmountResponceDTO(
                MappingUtils.convertToDTO(curDAO.findByCode(baseCurrencyCode).get()),
                MappingUtils.convertToDTO(curDAO.findByCode(targetCurrencyCode).get()),
                rateBaseOnTarget,
                amount,
                convertedAmount);
    }

    public ExchangeRateWithAmountResponceDTO Exchange(ExchangeRateReqDTO reqDTO) {
        String baseCode = reqDTO.getBaseCode();
        String targetCode = reqDTO.getTargetCode();
        BigDecimal amount = reqDTO.getRate();

        if (isExchangeRateInDB(baseCode, targetCode)) {
            return getResponceDTOonRate(baseCode, targetCode, amount);
        }
        else if (isBackExchangeRateInDB(baseCode, targetCode)) {
            return getResponceDTOonBackRate(baseCode, targetCode, amount);
        }
        else if (isUSDExchangeRate(baseCode, targetCode)) {
            return getResponceDTOonUSDRate(baseCode, targetCode, amount);
        }
        else {
            throw new NotFoundException("Для данных валют нет возможных курсов обмена");
        }
    }

}
