package com.exness.app.utils.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.DoubleStream;

public class CurrenciesUtils {

    private static final Logger logger = LogManager.getLogger(CurrenciesUtils.class);

    private double currencyBid;
    private double currencyAsk;

    public CurrenciesUtils(double theCurrencyBid, double theCurrencyAsk) {
        this.currencyBid = theCurrencyBid;
        this.currencyAsk = theCurrencyAsk;
    }

    private double getConvertCurrencyValue() {
        double[] numbers = {this.currencyBid, this.currencyAsk};
        return DoubleStream.of(numbers).average().getAsDouble();
    }

    public double convert(double currencyValue) {
        return currencyValue * getConvertCurrencyValue();
    }

}
