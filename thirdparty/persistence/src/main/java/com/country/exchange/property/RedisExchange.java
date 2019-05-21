package com.country.exchange.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "redis.exchange")
public class RedisExchange {
    private int port;
    private String host;
}
