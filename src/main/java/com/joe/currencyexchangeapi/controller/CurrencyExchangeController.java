package com.joe.currencyexchangeapi.controller;

import com.joe.currencyexchangeapi.enums.CurrencyEnum;
import com.joe.currencyexchangeapi.serivce.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency_exchange")
    public Mono<Map<String, Object>> convertAmountByCurrency(
            @RequestParam String source,
            @RequestParam String target,
            @RequestParam String amount
    ) {
        Map<String, Object> response = new HashMap<>();

        // 檢查amount是否可以正常轉換成數字
        if (!isValidAmount(amount)) {
            //回傳錯誤response
            String message = "failed, Unsupported amount conversion, plz request number thx. amount: %s";
            String formattedMessage = String.format(message, amount);
            log.error(formattedMessage);
            response.put("msg", formattedMessage);
        }
        // 檢查匯率資料是否存在
        else if (!CurrencyEnum.isValidCurrency(source) || !CurrencyEnum.isValidCurrency(target)) {
            //回傳錯誤response
            String message = "failed, Unsupported currency conversion. sourceCurrency: %s, targetCurrency: %s";
            String formattedMessage = String.format(message, CurrencyEnum.isValidCurrency(source), CurrencyEnum.isValidCurrency(target));
            log.error(formattedMessage);
            response.put("msg", formattedMessage);
        } else {
            try {
                String currencyExchangeAmount = currencyExchangeService.convertAmountByCurrency(source, target, parseAmount(amount));
                response.put("msg", "success");
                response.put("amount", currencyExchangeAmount);
            } catch (ParseException e) {
                String errorMessage = "failed, An error occurred during currency conversion.";
                log.error(errorMessage, e);
                response.put("msg", errorMessage);
            }
        }
        return Mono.just(response);
    }


    private BigDecimal parseAmount(String amount) throws ParseException {
        // 去除 amount 中的千分位符號
        String sanitizedAmount = amount.replace(",", "");
        // 使用 DecimalFormat 將去除千分位符號後的 amount 轉換為 BigDecimal
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(sanitizedAmount);
    }

    private boolean isValidAmount(String amount) {
        try {
            parseAmount(amount);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
