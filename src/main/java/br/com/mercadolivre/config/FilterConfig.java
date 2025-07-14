package br.com.mercadolivre.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SimpleAuthFilter> simpleAuthFilter() {
        FilterRegistrationBean<SimpleAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleAuthFilter());
        registrationBean.addUrlPatterns("/product_purchase/*"); // aplica o filtro nas rotas desejadas
        return registrationBean;
    }
}