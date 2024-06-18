package com.example.javaeetest2.Service;

import com.example.javaeetest2.DAO.CurrenciesDAO;
import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.example.javaeetest2.DTO.ExchangeRateResponseDTO;
import com.example.javaeetest2.DTO.ExchangeRateWithAmountRequestDTO;
import com.example.javaeetest2.DTO.ExchangeRateWithAmountResponceDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeService {
    private final String baseCurrencyCode;
    private final String targetCurrencyCode;
    private final BigDecimal amount;

    private final CurrenciesDAO curDAO = new CurrenciesDAO();

    public ExchangeService(ExchangeRateWithAmountRequestDTO exchangeDTO) {

        this.baseCurrencyCode = exchangeDTO.getBaseCurrencyCode();
        this.targetCurrencyCode = exchangeDTO.getTargetCurrencyCode();
        this.amount = exchangeDTO.getAmount();
    }

    public boolean isExchangeRateInDB() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        return exchangeRatesDAO.isExchangeRateOnCodes(baseCurrencyCode, targetCurrencyCode);
    }

    public boolean isBackExchangeRateInDB() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        return exchangeRatesDAO.isExchangeRateOnCodes(targetCurrencyCode, baseCurrencyCode);
    }

    public boolean isUSDExchangeRate() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        return exchangeRatesDAO.isExchangeRateOnCodes("USD", baseCurrencyCode) && exchangeRatesDAO.isExchangeRateOnCodes("USD", targetCurrencyCode);
    }

    public ExchangeRateWithAmountResponceDTO getResponceDTOonRate() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        BigDecimal convertedAmount = amount.multiply(exchangeRatesDAO.getRateOnCodes(baseCurrencyCode, targetCurrencyCode));
        ExchangeRateResponseDTO exchangeRateResponseDTO = exchangeRatesDAO.getExchangeRateOnCodes(baseCurrencyCode, targetCurrencyCode).orElseThrow();
        return new ExchangeRateWithAmountResponceDTO(
                exchangeRateResponseDTO.getCurrencyBase(),
                exchangeRateResponseDTO.getCurrencyTarget(),
                exchangeRateResponseDTO.getRate(),
                amount,
                convertedAmount);
    }

    public ExchangeRateWithAmountResponceDTO getResponceDTOonBackRate() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        BigDecimal convertedAmount = amount.divide(exchangeRatesDAO.getRateOnCodes(targetCurrencyCode, baseCurrencyCode), 3, RoundingMode.HALF_UP);
        return new ExchangeRateWithAmountResponceDTO(
                curDAO.getCurrencyOnCode(baseCurrencyCode).get(),
                curDAO.getCurrencyOnCode(targetCurrencyCode).get(),
                convertedAmount.divide(amount, 3, RoundingMode.HALF_UP),
                amount,
                convertedAmount);
    }

    public ExchangeRateWithAmountResponceDTO getResponceDTOonUSDRate() {
        ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
        BigDecimal rateBaseOnTarget =
                exchangeRatesDAO.getRateOnCodes("USD", baseCurrencyCode)
                        .divide(exchangeRatesDAO.getRateOnCodes("USD", targetCurrencyCode), 3, RoundingMode.HALF_UP);
        BigDecimal convertedAmount = amount.multiply(rateBaseOnTarget);
        ExchangeRateResponseDTO exchangeRateResponseDTO = exchangeRatesDAO.getExchangeRateOnCodes(baseCurrencyCode, targetCurrencyCode).orElseThrow();
        return new ExchangeRateWithAmountResponceDTO(
                exchangeRateResponseDTO.getCurrencyBase(),
                exchangeRateResponseDTO.getCurrencyTarget(),
                rateBaseOnTarget,
                amount,
                convertedAmount);
    }

    public ExchangeRateWithAmountResponceDTO Exchange() {
        if (isExchangeRateInDB()) {
            return getResponceDTOonRate();
        } else if (isBackExchangeRateInDB()) {
            return getResponceDTOonBackRate();
        } else if (isUSDExchangeRate()) {
            return getResponceDTOonUSDRate();
        } else {
            return null;
        }
    }

}
