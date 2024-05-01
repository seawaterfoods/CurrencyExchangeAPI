package com.joe.currencyexchangeapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "exchange-rate-setting")
public class ExchangeRatesConfig {
    private Map<String, Map<String, String>> currencies;
}
