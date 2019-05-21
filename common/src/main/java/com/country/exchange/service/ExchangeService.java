package com.country.exchange.service;

import com.country.exchange.dto.CurrencyCountry;

import java.util.Map;

public interface ExchangeService {

    interface ExchangeSource {
        Map<CurrencyCountry, String> mapExchangeCurrency(CurrencyCalculator calculator, double transferMoney);
        Map<CurrencyCountry, String> mapBeforeCurrency(CurrencyCalculator calculator);
    }

    Map<CurrencyCountry, String> calculate(ExchangeSource source, double transferMoney);
    Map<CurrencyCountry, String> beforeCalculate(ExchangeSource source);
}