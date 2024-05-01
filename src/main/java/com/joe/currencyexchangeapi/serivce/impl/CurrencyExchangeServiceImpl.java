package com.joe.currencyexchangeapi.serivce.impl;


import com.joe.currencyexchangeapi.config.ExchangeRatesConfig;
import com.joe.currencyexchangeapi.serivce.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    //依賴注入
    private final ExchangeRatesConfig exchangeRatesConfig;

    @Override
    public String convertAmountByCurrency(String sourceCurrency, String targetCurrency, BigDecimal amount) {
    }
}
