package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.DTO.ExchangeRateRequestDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRates")
public class ExchangeRatesServlet extends baseExchangeServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try{
            String json = objectMapper.writeValueAsString(dataService.getExchangeRatesList());
            writer.write(json);
        }catch (CastomException e){
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        try {
            validationService.isValidCode(req.getParameter("baseCurrencyCode"));
            validationService.isValidCode(req.getParameter("targetCurrencyCode"));
            validationService.isValidRate(req.getParameter("rate"));
            ExchangeRateRequestDTO exchangeRequestDTO = dataService.getDtoByRequest(
                    req.getParameter("baseCurrencyCode"),
                    req.getParameter("targetCurrencyCode"),
                    new BigDecimal(req.getParameter("rate")));
            writer.println(objectMapper.writeValueAsString(dataService.insertExchangeRate(exchangeRequestDTO)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
