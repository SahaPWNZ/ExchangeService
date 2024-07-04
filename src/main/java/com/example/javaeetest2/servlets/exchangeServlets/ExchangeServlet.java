package com.example.javaeetest2.servlets.exchangeServlets;

import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.service.ExchangeService;
import com.example.javaeetest2.servlets.BaseServlet;
import com.example.javaeetest2.utils.ValidationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet (value = "/exchange")
public class ExchangeServlet extends BaseServlet {
    ExchangeService service = new ExchangeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ValidationUtils.isValidExchangeRateReqDTO(
                    req.getParameter("from"),
                    req.getParameter("to"),
                    req.getParameter("amount"));

            ExchangeRateReqDTO requestWithAmountDTO = new ExchangeRateReqDTO(
                    req.getParameter("from"),
                    req.getParameter("to"),
                    new BigDecimal(req.getParameter("amount")));

            resp.getWriter().println(objectMapper.writeValueAsString(service.Exchange(requestWithAmountDTO)));
    }
}
