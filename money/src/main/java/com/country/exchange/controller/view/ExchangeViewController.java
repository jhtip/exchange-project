package com.country.exchange.controller.view;

import com.country.exchange.dto.CurrencyCountry;
import com.country.exchange.dto.SourceCountry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ExchangeViewController {
    @GetMapping("/")
    public String main(Model model) {
        Map<String, String> currencyCountry = Arrays.stream(CurrencyCountry.values()).collect(Collectors.toMap(CurrencyCountry::getCode, CurrencyCountry::getDesc));
        Map<String, String> sourceCountry = Arrays.stream(SourceCountry.values()).collect(Collectors.toMap(SourceCountry::getCode, SourceCountry::getDesc));
        model.addAttribute("currencyCountry", currencyCountry);
        model.addAttribute("sourceCountry", sourceCountry);
        return "index";
    }
}