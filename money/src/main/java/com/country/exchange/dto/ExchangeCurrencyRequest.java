package com.country.exchange.dto;


import lombok.Data;

import java.util.List;

@Data
public class ExchangeCurrencyRequest {
    private SourceCountry source;
    private List<CurrencyCountry> currencies;
    private double transferMoney;

}
