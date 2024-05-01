package com.joe.currencyexchangeapi;

import com.joe.currencyexchangeapi.config.ExchangeRatesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(ExchangeRatesConfig.class)
public class CurrencyExchangeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApiApplication.class, args);
    }

}
