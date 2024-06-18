package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = objectMapper.writeValueAsString(ratesDAO.getAllExchangeRates());
        writer.write(json);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        PrintWriter writer = resp.getWriter();
//
//        try {
//            ValidationService.isValidExchangeRatesRequest(req.getParameter("baseCurrencyCode"), req.getParameter("targetCurrencyCode"),
//                    req.getParameter("rate")); //поменять ошибки
//            ExchangeRateRequestDTO exchangeRequestDTO = new ExchangeRateRequestDTO(
//                    DataManagementService.getCurrencyOnCode(req.getParameter("baseCurrencyCode")),
//                    DataManagementService.getCurrencyOnCode(req.getParameter("targetCurrencyCode")),
//                    new BigDecimal(req.getParameter("rate")));
//            writer.println(objectMapper.writeValueAsString(DataManagementService.insertExchangeRate(exchangeRequestDTO)));
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//        } catch (CastomException e) {
//            resp.setStatus(e.getCODE_OF_EXCEPTION());
//            resp.getWriter().write(e.getMessage());
//        }
//
//
//    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
