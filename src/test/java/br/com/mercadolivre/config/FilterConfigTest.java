package br.com.mercadolivre.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import static org.assertj.core.api.Assertions.assertThat;

class FilterConfigTest {

    @Test
    void deveRegistrarFiltroCorretamente() {
        // Arrange
        FilterConfig filterConfig = new FilterConfig();

        // Act
        FilterRegistrationBean<br.com.mercadolivre.config.SimpleAuthFilter> registrationBean = filterConfig.simpleAuthFilter();

        // Assert
        assertThat(registrationBean).isNotNull();
        assertThat(registrationBean.getFilter()).isInstanceOf(br.com.mercadolivre.config.SimpleAuthFilter.class);
        assertThat(registrationBean.getUrlPatterns()).containsExactly("/product_purchase/*");
    }
}