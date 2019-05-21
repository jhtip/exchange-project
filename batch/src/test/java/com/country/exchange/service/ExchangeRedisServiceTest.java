package com.country.exchange.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExchangeRedisServiceTest {

    @Autowired
    @Qualifier("exchangeRedisTemplate")
    private RedisTemplate<String, String> exchangeRedisTemplate;

    @Test
    public void setKey() {
        System.out.println(exchangeRedisTemplate);
        SetOperations ops = exchangeRedisTemplate.opsForSet();
        exchangeRedisTemplate.opsForValue().set("this", "this");
        exchangeRedisTemplate.opsForValue().set("this", "that");

        Long saveCount = ops.add("test", "test1");
        log.info("redis 'SADD' count={}", saveCount);
    }

    @Test
    public void replaceKey() {
        Boolean test1 = exchangeRedisTemplate.delete("test");
        System.out.println(test1);
    }
}