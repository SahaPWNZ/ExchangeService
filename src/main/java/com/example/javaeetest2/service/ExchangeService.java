//package com.example.javaeetest2.service;
//
//import com.example.javaeetest2.dao.CurrenciesDAO;
//import com.example.javaeetest2.dao.ExchangeRatesDAO;
//import com.example.javaeetest2.dto.ExchangeRateReqDTO;
//import com.example.javaeetest2.dto.ExchangeRateResponseDTO;
//import com.example.javaeetest2.dto.ExchangeRateWithAmountResponceDTO;
//import com.example.javaeetest2.exceptions.NotFoundException;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//public class ExchangeService {
//    private final String baseCurrencyCode;
//    private final String targetCurrencyCode;
//    private final BigDecimal amount;
//
//    private final CurrenciesDAO curDAO = new CurrenciesDAO();
//    private final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
//    public ExchangeService(ExchangeRateReqDTO exchangeDTO) {
//        this.baseCurrencyCode = exchangeDTO.getBaseCode();
//        this.targetCurrencyCode = exchangeDTO.getTargetCode();
//        this.amount = exchangeDTO.getRate();
//    }
//
//    private boolean isExchangeRateInDB() {
//        return exchangeRatesDAO.isExchangeRateInDB(baseCurrencyCode,targetCurrencyCode);
//    }
//
//    private boolean isBackExchangeRateInDB() {
//        return exchangeRatesDAO.isExchangeRateInDB(targetCurrencyCode, baseCurrencyCode);
//    }
//
//    private boolean isUSDExchangeRate() {
//        return exchangeRatesDAO.isExchangeRateInDB("USD", baseCurrencyCode) && exchangeRatesDAO.isExchangeRateInDB("USD", targetCurrencyCode);
//    }
//
//    private ExchangeRateWithAmountResponceDTO getResponceDTOonRate() {
//        BigDecimal convertedAmount = amount.multiply(exchangeRatesDAO.getRateOnCodes(baseCurrencyCode, targetCurrencyCode)).setScale(2, RoundingMode.HALF_UP);
//        ExchangeRateResponseDTO exchangeRateResponseDTO = exchangeRatesDAO.findByCodes(baseCurrencyCode, targetCurrencyCode).orElseThrow();
//        return new ExchangeRateWithAmountResponceDTO(
//                exchangeRateResponseDTO.getCurrencyBase(),
//                exchangeRateResponseDTO.getCurrencyTarget(),
//                exchangeRateResponseDTO.getRate(),
//                amount,
//                convertedAmount);
//    }
//
//    private ExchangeRateWithAmountResponceDTO getResponceDTOonBackRate() {
//        BigDecimal convertedAmount = amount.divide(exchangeRatesDAO.getRateOnCodes(targetCurrencyCode, baseCurrencyCode), 2, RoundingMode.HALF_UP);
//        return new ExchangeRateWithAmountResponceDTO(
//                curDAO.findByCode(baseCurrencyCode).get(),
//                curDAO.findByCode(targetCurrencyCode).get(),
//                convertedAmount.divide(amount, 6, RoundingMode.HALF_UP),
//                amount,
//                convertedAmount);
//    }
//
//    public ExchangeRateWithAmountResponceDTO getResponceDTOonUSDRate() {
//        BigDecimal rateBaseOnTarget =
//                exchangeRatesDAO.getRateOnCodes("USD", targetCurrencyCode)
//                        .divide(exchangeRatesDAO.getRateOnCodes("USD", baseCurrencyCode), 2, RoundingMode.HALF_UP);
//        BigDecimal convertedAmount = amount.multiply(rateBaseOnTarget).setScale(2, RoundingMode.HALF_UP);
//        return new ExchangeRateWithAmountResponceDTO(
//                curDAO.findByCode(baseCurrencyCode).get(),
//                curDAO.findByCode(targetCurrencyCode).get(),
//                rateBaseOnTarget,
//                amount,
//                convertedAmount);
//    }
//
//    public ExchangeRateWithAmountResponceDTO Exchange() {
//        if (isExchangeRateInDB()) {
//            return getResponceDTOonRate();
//        } else if (isBackExchangeRateInDB()) {
//            return getResponceDTOonBackRate();
//        } else if (isUSDExchangeRate()) {
//            return getResponceDTOonUSDRate();
//        } else {
//            throw new NotFoundException("Для данных валют нет возможных курсов обмена");
//        }
//    }
//
//}
