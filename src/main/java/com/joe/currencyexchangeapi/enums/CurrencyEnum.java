package com.joe.currencyexchangeapi.enums;

public enum CurrencyEnum {
    TWD("TWD"),
    JPY("JPY"),
    USD("USD");

    private final String code;

    CurrencyEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // 檢查貨幣是否有效
    public static boolean isValidCurrency(String currencyCode) {
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            if (currency.getCode().equals(currencyCode)) {
                return true;
            }
        }
        return false;
    }
}
