package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.DTO.ExchangeRateRequestDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import com.example.javaeetest2.Service.DataManagementService;
import com.example.javaeetest2.Service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(value = "/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    ExchangeRatesDAO ratesDAO = new ExchangeRatesDAO();
    ObjectMapper objectMapper = new ObjectMapper();
    ValidationService validationService = new ValidationService();
    DataManagementService dataService = new DataManagementService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
            ExchangeRateRequestDTO exchangeRequestDTO = dataService.getDtoByRequest(req);
            writer.println(objectMapper.writeValueAsString(dataService.insertExchangeRate(exchangeRequestDTO)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
