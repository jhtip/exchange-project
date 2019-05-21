package com.country.exchange.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeResult {
    private Map<String, Double> quotes;
}
