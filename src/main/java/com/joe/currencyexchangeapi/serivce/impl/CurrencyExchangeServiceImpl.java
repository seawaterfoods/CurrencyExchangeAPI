package com.joe.currencyexchangeapi.serivce.impl;


import com.joe.currencyexchangeapi.config.ExchangeRatesConfig;
import com.joe.currencyexchangeapi.serivce.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    //依賴注入
    private final ExchangeRatesConfig exchangeRatesConfig;

    @Override
    public String convertAmountByCurrency(String sourceCurrency, String targetCurrency, BigDecimal amount) {

        // 取得匯率並進行轉換
        Map<String, Map<String, String>> currencies = exchangeRatesConfig.getCurrencies();

// 將匯率字串轉換為 BigDecimal
        BigDecimal exchangeRate = new BigDecimal(currencies.get(sourceCurrency).get(targetCurrency));
        BigDecimal currencyExchangeAmount = amount.multiply(exchangeRate);

        // 四捨五入到小數點第二位
        currencyExchangeAmount = currencyExchangeAmount.setScale(2, RoundingMode.HALF_UP);

        // 格式化金額，加上千分位分隔符
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(currencyExchangeAmount);
    }
}
