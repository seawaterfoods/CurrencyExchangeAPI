package com.joe.currencyexchangeapi.serivce.impl;


import com.joe.currencyexchangeapi.data.ExchangeRates;
import com.joe.currencyexchangeapi.serivce.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private ExchangeRates exchangeRates;
    public CurrencyExchangeServiceImpl(ExchangeRates exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
