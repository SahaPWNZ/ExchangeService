package com.example.javaeetest2.servlets.Filters;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.exceptions.CastomException;
import com.example.javaeetest2.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/exchange")
public class ExchangeFilter implements Filter {
    ValidationService validationService = new ValidationService();
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try{
            validationService.isValidCode( req.getParameter("from"));
            validationService.isValidCode(req.getParameter("to"));
            validationService.isValidRate(req.getParameter("amount"));
            filterChain.doFilter(req,resp);
        }catch(CastomException e){
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }

    }
}
