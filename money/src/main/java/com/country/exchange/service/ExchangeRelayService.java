package com.country.exchange.service;

import com.country.exchange.dto.ExchangeCurrencyApiResult;
import com.country.exchange.dto.ExchangeCurrencyRequest;
import com.country.exchange.dto.ExchangeCurrencyResponse;
import com.country.exchange.dto.ExchangeRedisDto;
import com.country.exchange.exception.BusinessError;
import com.country.exchange.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.country.exchange.service.ExchangeService.ExchangeSource;

@Service
@RequiredArgsConstructor
public class ExchangeRelayService {

    private final ExchangeMoneyRateRelayService exchangeMoneyRateRelayService;
    private final ExchangeRedisService exchangeRedisService;
    private final ExchangeService exchangeService;

    public ExchangeCurrencyResponse getCurrency(@Valid ExchangeCurrencyRequest request) {

        ExchangeSource source = getCurrencyData(request);

        return ExchangeCurrencyResponse.builder()
                .source(request.getSource().getCode())
                .currency(exchangeService.calculate(source, request.getTransferMoney()))
                .beforeCalculate(exchangeService.beforeCalculate(source)).build();
    }

    private ExchangeSource getCurrencyData(ExchangeCurrencyRequest request) {
        Optional<ExchangeRedisDto> currencyData = exchangeRedisService.getCurrencyData(request.getSource().getCode());
        if (currencyData.isPresent()) return currencyData.get();
        return getLiveDataFromApi(request);
    }

    private ExchangeCurrencyApiResult getLiveDataFromApi(ExchangeCurrencyRequest request) {
        String countries = request.getCurrencies().stream().map(currency -> currency.getCode()).collect(Collectors.joining(","));
        ExchangeCurrencyApiResult liveRate = exchangeMoneyRateRelayService.getLiveRate(request.getSource().getCode(), countries);
        if (invalidData(liveRate)) throw new BusinessException(BusinessError.NO_DATA);
        return liveRate;
    }

    private boolean invalidData(ExchangeCurrencyApiResult liveRate) {
        return !liveRate.isSuccess() || ObjectUtils.isEmpty(liveRate.getQuotes());
    }

}
