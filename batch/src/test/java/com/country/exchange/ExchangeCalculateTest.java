package com.country.exchange;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ExchangeCalculateTest {

    @Test
    public void formatCurrency() {
        double usd = 1194.745;
        float  usd1 = 1194.7404F;
        DecimalFormat df = new DecimalFormat("#,###.##");
        df.setRoundingMode(RoundingMode.DOWN);

        System.out.println(String.format(Locale.US, "%.2f", usd1));
        String format = df.format(usd);
        System.out.println(format);




    }
}