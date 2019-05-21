package com.country.exchange.service;


import com.country.exchange.dto.ExchangeCurrencyApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeMoneyRateRelayService {

    @Value("${exchange.url}")
    private String apiUrl;

    @Value("${exchange.access-key}")
    private String accessKey;

    private final RestTemplate exchangeRestTemplate;

    private final ObjectMapper objectMapper;


    public ExchangeCurrencyApiResult getLiveRate(String code, String countries) {
        String requestUrl = getRequest(code, countries);

        ExchangeCurrencyApiResult result = exchangeRestTemplate.getForObject(requestUrl, ExchangeCurrencyApiResult.class);

        log.info("{}", result);

        return result;
    }

    private String getRequest(String source, String currenciesString) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/api/live")
                .queryParam("access_key", accessKey)
                .queryParam("source", source)
                .queryParam("currencies", currenciesString)
                .build()
                .toUriString();
    }
}
