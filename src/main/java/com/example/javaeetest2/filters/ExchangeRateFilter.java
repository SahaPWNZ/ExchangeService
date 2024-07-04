package com.example.javaeetest2.filters;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.exceptions.CastomException;
import com.example.javaeetest2.utils.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/exchangeRate/*")
public class ExchangeRateFilter implements Filter {
    ValidationUtils validationUtils = new ValidationUtils();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            validationUtils.checkPathFromServlet(req.getPathInfo());
            if (req.getMethod().equalsIgnoreCase("GET")) {
                filterChain.doFilter(req, resp);
            } else if (req.getMethod().equalsIgnoreCase("PATCH")) {
                validationUtils.isValidRate(req.getParameter("rate"));
                filterChain.doFilter(req, resp);
            }
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
}
