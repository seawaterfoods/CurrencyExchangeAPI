package com.joe.currencyexchangeapi.serivce;

import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyExchangeService {
    String convertAmountByCurrency(String sourceCurrency, String targetCurrency, BigDecimal amount);
}
