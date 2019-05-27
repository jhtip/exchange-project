package com.country.exchange.service;

import com.country.exchange.dto.ExchangeRedisDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRedisService {
    private final RedisTemplate<String, String> exchangeRedisTemplate;
    private final Gson gson;

    public void syncCurrencyData(Map<String, String> currencyMap) {
        log.info("currencyMap {}", currencyMap);
        currencyMap.entrySet().forEach(
                data -> exchangeRedisTemplate.opsForValue().set(data.getKey(),
                        data.getValue()));
    }

    public Optional<ExchangeRedisDto> getCurrencyData(String code) {
        String result = exchangeRedisTemplate.opsForValue().get(code);
        ExchangeRedisDto exchangeRedisDto = gson.fromJson(result, ExchangeRedisDto.class);
        log.info("{}", exchangeRedisDto);
        return StringUtils.isEmpty(result) ? Optional.empty() : Optional.ofNullable(gson.fromJson(result, ExchangeRedisDto.class));
    }
}
