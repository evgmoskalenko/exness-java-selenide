package com.exness.app.utils.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static io.restassured.RestAssured.get;

//TODO: remove from framework
public class CurrenciesConverterUtils {

    private static final String API_PROVIDER = "http://api.fixer.io/";

    public static void main(String[] args) {

//        1 GBP = 1.29 USD
//        10 GBP = 12.89 USD
//        100 GBP = 128.94 USD
//        1000 GBP = 1289.4 USD

        double a = (1.28966+1.28914)/2;
        System.out.println(a);

        double conversion = 1.2894; // {"name":"GBP/USD","bid":"1.28914","ask":"1.28966"},
        double conversion1 = conversion * 1;
        double conversion2 = conversion * 10;
        double conversion3 = conversion * 100;
        double conversion4 = conversion * 1000;

        double c1 = new BigDecimal(conversion1).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double c2 = new BigDecimal(conversion2).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double c3 = new BigDecimal(conversion3).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double c4 = new BigDecimal(conversion4).setScale(2, RoundingMode.HALF_UP).doubleValue();

        System.out.println(conversion);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

    }

    public static double convert(String fromCurrencyCode, String toCurrencyCode) {

        if ((fromCurrencyCode != null && !fromCurrencyCode.isEmpty())
                && (toCurrencyCode != null && !toCurrencyCode.isEmpty())) {

            CurrencyConversionResponse response = get(API_PROVIDER + "/latest?base=" + fromCurrencyCode)
                    .as(CurrencyConversionResponse.class);

            if(response != null) {
                String rate = response.getRates().get(toCurrencyCode);
                return Double.valueOf((rate != null)?rate:"0.0");
            }

        }

        return 0.0;
    }

}
