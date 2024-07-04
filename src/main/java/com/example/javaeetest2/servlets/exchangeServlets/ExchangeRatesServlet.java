package com.example.javaeetest2.servlets.exchangeServlets;

import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.servlets.BaseServlet;
import com.example.javaeetest2.utils.ValidationUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRates")
public class ExchangeRatesServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper.writeValue(resp.getWriter(), dataService.getExchangeRatesList());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ValidationUtils.isValidExchangeRateReqDTO(
                req.getParameter("baseCurrencyCode"),
                req.getParameter("targetCurrencyCode"),
                req.getParameter("rate"));

        ExchangeRateReqDTO exchangeRequestDTO = new ExchangeRateReqDTO(
                req.getParameter("baseCurrencyCode"),
                req.getParameter("targetCurrencyCode"),
                new BigDecimal(req.getParameter("rate")));

        objectMapper.writeValue(resp.getWriter(), dataService.insertExchangeRate(exchangeRequestDTO));
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}
