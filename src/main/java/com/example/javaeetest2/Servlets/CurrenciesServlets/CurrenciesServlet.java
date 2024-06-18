package com.example.javaeetest2.Servlets.CurrenciesServlets;

import java.io.*;
import java.sql.SQLException;

import com.example.javaeetest2.DAO.CurrenciesDAO;
import com.example.javaeetest2.DTO.CurrencyRequestDTO;
import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import com.example.javaeetest2.Service.DataManagementService;
import com.example.javaeetest2.Service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = "/currencies")
public class CurrenciesServlet extends CurrencyBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            String json = objectMapper.writeValueAsString(dataService.getCurrenciesList());
            PrintWriter writer = response.getWriter();
            writer.println(json);
        } catch (CastomException e) {
            response.setStatus(e.getCODE_OF_EXCEPTION());
            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        CurrencyRequestDTO reqDTO = new CurrencyRequestDTO(request.getParameter("Code"),
                request.getParameter("FullName"), request.getParameter("Sign"));
        try {
            validationService.isValidCurrencyDTO(reqDTO);
            writer.println(objectMapper.writeValueAsString(dataService.insertCurrency(reqDTO)));
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (CastomException e) {
            response.setStatus(e.getCODE_OF_EXCEPTION());
            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }

    public void destroy() {
    }
}