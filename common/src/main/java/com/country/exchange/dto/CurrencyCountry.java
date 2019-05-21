package com.country.exchange.dto;

public enum CurrencyCountry {
    KRW("KRW", "한국"),
    JPN("JPN", "일본"),
    PHP("PHP", "필리핀");

    String code;
    String desc;

    CurrencyCountry(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CurrencyCountry of(String code) {
        for (CurrencyCountry value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}