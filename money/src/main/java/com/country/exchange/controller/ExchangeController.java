package com.country.exchange.controller;

import com.country.exchange.dto.ExchangeCurrencyRequest;
import com.country.exchange.service.ExchangeRelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeRelayService exchangeRelayService;

    @GetMapping("exchange/info")
    public Object getExchangeInfo(@Valid ExchangeCurrencyRequest request) {
        return exchangeRelayService.getCurrency(request);
    }

}
