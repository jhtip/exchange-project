package com.country.exchange.dto;

import com.country.exchange.service.CurrencyCalculator;
import com.country.exchange.service.ExchangeService;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ExchangeRedisDto implements ExchangeService.ExchangeSource {
    private Map<CurrencyCountry, Double> quotes;

    public ExchangeRedisDto(Map<CurrencyCountry, Double> quotes) {
        this.quotes = quotes;
    }

    @Override
    public Map<CurrencyCountry, String> mapExchangeCurrency(CurrencyCalculator calculator, double transferMoney) {
        return mapResult(calculator, transferMoney);
    }

    private Map<CurrencyCountry, String> mapResult(CurrencyCalculator calculator, double transferMoney) {
        return quotes.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(e -> e.getKey(),
                                e -> calculator.calculateTotalWithFormat(e.getValue(), transferMoney)));
    }

    @Override
    public Map<CurrencyCountry, String> mapBeforeCurrency(CurrencyCalculator calculator) {
        return mapResult(calculator, 1);
    }
}