package com.example.javaeetest2.Servlets.CurrenciesServlets;

import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/currency/*")
public class CurrencyServlet extends baseCurrencyServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            String code = request.getPathInfo().substring(1);
            validationService.isValidCode(code);
            var respDTO = dataService.getCurrencyOnCode(code);
            String json = objectMapper.writeValueAsString(respDTO);
            PrintWriter writer = response.getWriter();
            writer.println(json);
        } catch (CastomException e) {
            response.setStatus(e.getCODE_OF_EXCEPTION());
            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
    public void destroy() {
        super.destroy();
    }
}