package com.country.exchange.service;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Component
public class CurrencyCalculator {
    public String calculateTotalWithFormat(Double value, double transferMoney) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(value * transferMoney);
    }
}
