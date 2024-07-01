package com.example.javaeetest2.servlets.ExchangeServlets;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.exceptions.CastomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRate/*")
public class ExchangeRateServlet extends BaseExchangeServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String currencyBaseCode = req.getPathInfo().substring(1, 4);
            String currencyTargetCode = req.getPathInfo().substring(4, 7);
            resp.getWriter().println(objectMapper.writeValueAsString(dataService.getExchangeRateByCodes(currencyBaseCode, currencyTargetCode)));
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("PATCH".equals(req.getMethod())) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ExchangeRateReqDTO exchangeRequestDTO = new ExchangeRateReqDTO(
                    req.getPathInfo().substring(1, 4),
                    req.getPathInfo().substring(4, 7),
                    new BigDecimal(req.getParameter("rate")));
            resp.getWriter().println(objectMapper.writeValueAsString(dataService.updateRate(exchangeRequestDTO)));
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }


    }
}
