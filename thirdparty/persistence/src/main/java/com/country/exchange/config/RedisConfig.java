package com.country.exchange.config;


import com.country.exchange.property.RedisExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
@EnableConfigurationProperties(RedisExchange.class)
public class RedisConfig {

    @Bean(name = "redisExchangeConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory(RedisExchange exchange) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(exchange.getHost(), exchange.getPort());
        factory.setValidateConnection(true);
        return factory;
    }

    @Bean(name = "exchangeRedisTemplate")
    public RedisTemplate<String, String> redisTemplate(@Qualifier("redisExchangeConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
