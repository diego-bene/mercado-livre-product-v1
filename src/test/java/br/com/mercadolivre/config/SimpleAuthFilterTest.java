package br.com.mercadolivre.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimpleAuthFilterTest {

    private SimpleAuthFilter filter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;
    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws IOException {
        filter = new SimpleAuthFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
        responseWriter = new StringWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    void testPreflightRequest() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("OPTIONS");

        filter.doFilter(request, response, chain);

        verify(response).setHeader("Access-Control-Allow-Origin", "*");
        verify(response).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        verify(response).setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertEquals("Preflight OK", responseWriter.toString());
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void testUnauthorizedAccess() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        assertEquals("Acesso nao autorizado!", responseWriter.toString());
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void testAuthorizedAccess() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("GET");
        String validToken = "Bearer " + Base64.getEncoder().encodeToString("54321-teste-MELI".getBytes());
        when(request.getHeader("Authorization")).thenReturn(validToken);

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        verify(response, never()).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}