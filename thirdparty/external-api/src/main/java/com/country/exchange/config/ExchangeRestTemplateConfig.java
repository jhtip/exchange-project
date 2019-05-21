package com.country.exchange.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExchangeRestTemplateConfig {

    @Bean(name = "exchangeRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .build();
    }
}
