package com.example.javaeetest2.servlets.currenciesServlets;

import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.servlets.BaseServlet;
import com.example.javaeetest2.utils.ValidationUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/currencies")
public class CurrenciesServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        objectMapper.writeValue(response.getWriter(), dataService.getCurrenciesList());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CurrencyRequestDTO curDTO = new CurrencyRequestDTO(
                request.getParameter("Code"),
                request.getParameter("FullName"),
                request.getParameter("Sign"));

        ValidationUtils.isValidCurrencyDTO(curDTO);

        objectMapper.writeValue(response.getWriter(), dataService.insertCurrency(curDTO));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}