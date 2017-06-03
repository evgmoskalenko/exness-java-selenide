package com.exness.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Currencies {

    USD("USD", "Американский доллар"),
    EUR("EUR", "Евро"),
    CHF("CHF", "Швейцарский франк"),
    JPY("JPY", "Японская иена"),
    AUD("AUD", "Австралийский доллар"),
    CAD("CAD", "Канадский доллар"),

    UAH("UAH", "Украинская гривна"),
    RUB("RUB", "Российский рубль");

    private static final Logger logger = LogManager.getLogger(Currencies.class);

    private String currencyShortProps;
    private String currencyLongProps;

    Currencies(final String theCurrencyShortProps, final String theCurrencyLongProps) {
        this.currencyShortProps = theCurrencyShortProps;
        this.currencyLongProps = theCurrencyLongProps;
    }

    public String getShortProps() {
        return currencyShortProps;
    }

    public String getLongProps() {
        return currencyLongProps;
    }

}
