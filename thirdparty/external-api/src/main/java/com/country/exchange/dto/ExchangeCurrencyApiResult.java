package com.country.exchange.dto;

import com.country.exchange.service.CurrencyCalculator;
import com.country.exchange.service.ExchangeService;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Data
public class ExchangeCurrencyApiResult implements ExchangeService.ExchangeSource {

    private boolean success;
    private String source;
    private Map<String, Double> quotes = new HashMap<>();


    @Override
    public Map<CurrencyCountry, String> mapExchangeCurrency(CurrencyCalculator calculator, double transferMoney) {
        int length = source.length();
        return mapResult(calculator, transferMoney, length);
    }

    private Map<CurrencyCountry, String> mapResult(CurrencyCalculator calculator, double transferMoney, int length) {
        return quotes.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(e -> CurrencyCountry.of(e.getKey().substring(length)),
                                e -> calculator.calculateTotalWithFormat(e.getValue(), transferMoney)));
    }

    @Override
    public Map<CurrencyCountry, String> mapBeforeCurrency(CurrencyCalculator calculator) {
        int length = source.length();
        return mapResult(calculator, 1, length);
    }

    public boolean isValidData() {
        return success && !ObjectUtils.isEmpty(quotes) && quotes.size() > 0;
    }
}