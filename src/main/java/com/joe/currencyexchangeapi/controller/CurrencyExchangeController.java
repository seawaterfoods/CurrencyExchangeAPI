package com.joe.currencyexchangeapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/currency_exchange")
public class CurrencyExchangeController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/testMono")
    public Mono<String> testMono() {
        return Mono.just("Hello, Reactive");
    }
}
