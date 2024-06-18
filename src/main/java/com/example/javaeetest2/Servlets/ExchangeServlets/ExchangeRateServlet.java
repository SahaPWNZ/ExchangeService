package com.example.javaeetest2.Servlets.ExchangeServlets;

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

@WebServlet(value = "/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    ValidationService validationService = new ValidationService();
    DataManagementService dataService = new DataManagementService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        resp.setContentType("application/json");
        try {
            validationService.checkPathFromServlet(req.getPathInfo());
            String currencyBaseCode = req.getPathInfo().substring(1, 4);
            String currencyTargetCode = req.getPathInfo().substring(4, 7);
            PrintWriter writer = resp.getWriter();
            writer.println(objectMapper.writeValueAsString(dataService.getExchangeRateByCodes(currencyBaseCode, currencyTargetCode)));

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

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        try {
            validationService.checkPathFromServlet(req.getPathInfo());
            validationService.isValidRate(req.getParameter("rate"));
            ExchangeRateRequestDTO exchangeRequestDTO = new ExchangeRateRequestDTO(
                    req.getPathInfo().substring(1, 4),
                    req.getPathInfo().substring(4, 7),
                    new BigDecimal(req.getParameter("rate")));
            writer.println(objectMapper.writeValueAsString(dataService.updateRate(exchangeRequestDTO)));
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }


    }
}
