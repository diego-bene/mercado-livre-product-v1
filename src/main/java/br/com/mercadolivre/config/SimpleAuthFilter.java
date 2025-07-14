package br.com.mercadolivre.config;

import java.io.IOException;
import java.util.Base64;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SimpleAuthFilter implements Filter {

    private final String expectedBase64 = "Bearer " + Base64.getEncoder().encodeToString("54321-teste-MELI".getBytes());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        
        if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
            httpResp.setHeader("Access-Control-Allow-Origin", "*");
            httpResp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpResp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            httpResp.setStatus(HttpServletResponse.SC_OK);
            httpResp.getWriter().write("Preflight OK");
            return;
        }

        String headerValue = httpReq.getHeader("Authorization");
        
        if (headerValue == null || !headerValue.equals(expectedBase64)) {
            httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResp.getWriter().write("Acesso nao autorizado!");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}


