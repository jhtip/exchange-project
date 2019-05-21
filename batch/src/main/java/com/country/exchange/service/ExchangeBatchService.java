package com.country.exchange.service;

import com.country.exchange.dto.CurrencyCountry;
import com.country.exchange.dto.ExchangeCurrencyApiResult;
import com.country.exchange.dto.ExchangeRedisDto;
import com.country.exchange.dto.SourceCountry;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeBatchService {

    private final ExchangeMoneyRateRelayService exchangeMoneyRateRelayService;
    private final ExchangeRedisService exchangeRedisService;
    private final Gson gson;

    public void syncData() {

        String countries = Arrays.stream(CurrencyCountry.values()).map(currency -> currency.getCode()).collect(Collectors.joining(","));

        // Basic Plan 이 아니기때문에 (돈이 들어감) USD 외에 못 가져옴
        ArrayList<ExchangeCurrencyApiResult> result = new ArrayList<>();
        for (SourceCountry value : SourceCountry.values()) {
            result.add(exchangeMoneyRateRelayService.getLiveRate(value.getCode(), countries));
        }

        log.info("ExchangeCurrencyApiResult = {}", result);

        syncCurrencyData(result);

    }

    private void syncCurrencyData(ArrayList<ExchangeCurrencyApiResult> result) {
        List<ExchangeCurrencyApiResult> mappedData = getCurrencyMappedData(result);

        if(ObjectUtils.isEmpty(mappedData)) return;

        exchangeRedisService.syncCurrencyData(getCurrencyMap(mappedData));
    }

    private List<ExchangeCurrencyApiResult> getCurrencyMappedData(ArrayList<ExchangeCurrencyApiResult> result) {
        return result.stream().filter(ExchangeCurrencyApiResult::isSuccess).collect(Collectors.toList());
    }

    private Map<String, String> getCurrencyMap(List<ExchangeCurrencyApiResult> exchangeCurrencyApiResults) {
        return exchangeCurrencyApiResults.stream()
                .collect(Collectors.toMap(
                        data -> data.getSource(),
                        data -> mapCurrencyForRedis(data.getQuotes(), data.getSource().length())));
    }

    private String mapCurrencyForRedis(Map<String, Double> quotes, int length) {
        Map<CurrencyCountry, Double> map = quotes.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(e -> CurrencyCountry.of(e.getKey().substring(length)),
                                e -> e.getValue()));

        return gson.toJson(new ExchangeRedisDto(map));
    }
}
