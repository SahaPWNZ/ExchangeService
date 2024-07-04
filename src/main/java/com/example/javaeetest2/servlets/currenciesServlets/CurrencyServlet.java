package com.example.javaeetest2.servlets.currenciesServlets;

import com.example.javaeetest2.servlets.BaseServlet;
import com.example.javaeetest2.utils.ValidationUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/currency/*")
public class CurrencyServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String code = request.getPathInfo().substring(1);
            ValidationUtils.isValidCode(code);

            response.getWriter().println(objectMapper.writeValueAsString(dataService.getCurrencyOnCode(code)));
    }
}