package com.country.exchange.dto;

public enum SourceCountry {
    USD("USD", "미국"),
    AUD("AUD", "호주");

    String code;
    String desc;

    SourceCountry(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SourceCountry of(String code) {
        for (SourceCountry value : values()) {
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
