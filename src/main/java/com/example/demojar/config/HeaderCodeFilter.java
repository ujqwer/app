package com.example.demojar.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class HeaderCodeFilter implements Filter {

    private final String expectedCode="123";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String headerCode = httpServletRequest.getHeader("Baby");

        if (expectedCode.equals(headerCode)) {
            // Allow access to the API
            chain.doFilter(request, response);
        } else {
            // Deny access to the API
            chain.doFilter(request, response);
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            httpServletResponse.getWriter().write("Sorry, you are unauthorized.");
        }
    }
}
