package com.example.javaeetest2.servlets.exchangeServlets;

import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.servlets.BaseServlet;
import com.example.javaeetest2.utils.ValidationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRate/*")
public class ExchangeRateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            ValidationUtils.checkPathFromServlet(req.getPathInfo());

            String currencyBaseCode = req.getPathInfo().substring(1, 4);
            String currencyTargetCode = req.getPathInfo().substring(4, 7);

            objectMapper.writeValue(resp.getWriter(),dataService.getExchangeRateByCodes(currencyBaseCode, currencyTargetCode) );
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
            ValidationUtils.checkPathFromServlet(req.getPathInfo());
            ValidationUtils.isValidRate(req.getParameter("rate"));

            ExchangeRateReqDTO exchangeRequestDTO = new ExchangeRateReqDTO(
                    req.getPathInfo().substring(1, 4),
                    req.getPathInfo().substring(4, 7),
                    new BigDecimal(req.getParameter("rate")));

            objectMapper.writeValue(resp.getWriter(), dataService.updateRate(exchangeRequestDTO));
    }
}
