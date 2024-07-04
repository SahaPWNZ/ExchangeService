package com.example.javaeetest2.servlets.exchangeServlets;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.exceptions.CastomException;
import com.example.javaeetest2.servlets.BaseServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRates")
public class ExchangeRatesServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            resp.getWriter().write(objectMapper.writeValueAsString(dataService.getExchangeRatesList()));
        }catch (CastomException e){
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
           ExchangeRateReqDTO exchangeRequestDTO = new ExchangeRateReqDTO(
                    req.getParameter("baseCurrencyCode"),
                    req.getParameter("targetCurrencyCode"),
                    new BigDecimal(req.getParameter("rate")));
            resp.getWriter().println(objectMapper.writeValueAsString(dataService.insertExchangeRate(exchangeRequestDTO)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }


}
