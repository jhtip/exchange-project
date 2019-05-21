package com.country.exchange.service;

import com.country.exchange.dto.CurrencyCountry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultExchangeService implements ExchangeService{

    private final CurrencyCalculator calculator;

    @Override
    public Map<CurrencyCountry, String> calculate(ExchangeSource source, double transferMoney) {
        return source.mapExchangeCurrency(calculator, transferMoney);
    }

    @Override
    public Map<CurrencyCountry, String> beforeCalculate(ExchangeSource source) {
        return source.mapBeforeCurrency(calculator);
    }
}