package com.example.javaeetest2.Servlets.Filters;

import com.example.javaeetest2.DTO.CurrencyRequestDTO;
import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import com.example.javaeetest2.Service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/currencies")
public class CurrenciesFilter implements Filter {
    ValidationService validationService = new ValidationService();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            if (req.getMethod().equalsIgnoreCase("POST")) {
                validationService.isValidCurrencyDTO(new CurrencyRequestDTO(req.getParameter("Code"),
                        req.getParameter("FullName"), req.getParameter("Sign")));
                filterChain.doFilter(req, resp);
            } else if (req.getMethod().equalsIgnoreCase("GET")) {
                filterChain.doFilter(req, resp);
            }
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }

    }
}
