package com.country.exchange.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExchangeCurrencyResponse {
    private String source;
    private Map<CurrencyCountry, String> currency;
    private Map<CurrencyCountry, String> beforeCalculate;
}