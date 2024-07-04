package com.example.javaeetest2.filters;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.exceptions.CastomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionsHandlingFilter extends HttpFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) res;
        try {
            super.doFilter(req, res, chain);
        } catch (CastomException e) {
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
}
