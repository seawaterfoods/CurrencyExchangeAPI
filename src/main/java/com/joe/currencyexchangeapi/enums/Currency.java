package com.joe.currencyexchangeapi.enums;

public enum Currency {
    TWD("TWD"),
    JPY("JPY"),
    USD("USD");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
