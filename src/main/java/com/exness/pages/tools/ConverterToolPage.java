package com.exness.pages.tools;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.exness.app.utils.converter.CurrenciesUtils;
import com.exness.app.wrappers.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yandex.qatools.allure.annotations.Step;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ConverterToolPage extends BasePage<ConverterToolPage> {

    private static final Logger logger = LogManager.getLogger(ConverterToolPage.class);

    @Override
    protected String getUrl() {
        return baseUrl + "/intl/ru/tools/converter/";
    }

    public enum CurrenciesTabs {
        CURR_TAB_FROM, CURR_TAB_TO
    }

    private ElementsCollection converterTabItems() {
        return $$(".converter-tabItem");
    }

    public ElementsCollection getCurrenciesFromCommonList() {
        return $$(".converter-currenciesItem");
    }

    public ElementsCollection getCurrenciesFromPopularList() {
        return $$(".converter-popularItem");
    }

    @Step("Choose currencies converter tab: \"{0}\"")
    public ConverterToolPage chooseCurrencyConverterTab(CurrenciesTabs tab) {
        switch (tab) {
            case CURR_TAB_FROM:
                converterTabItems().get(0).click();
                break;
            case CURR_TAB_TO:
                converterTabItems().get(1).click();
                break;
        }
        return this;
    }

    public double getTabCurrencyValue() {
        String value = $(".converter-tabItem__selected").$(".ui-input").attr("value");
        return Double.parseDouble(value);
    }

    public double getConvertedCurrencyValue(double currencyValue, double currencyBid, double currencyAsk) {
        CurrenciesUtils currenciesUtils = new CurrenciesUtils(currencyBid, currencyAsk);
        double convertedValue = currenciesUtils.convert(currencyValue);
        return new BigDecimal(convertedValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Step("Fill currencies value \"{0}\"")
    public ConverterToolPage setTabCurrencyValue(String currencyValue) {
        $(".converter-tabItem__selected").$(".ui-input").val(currencyValue);
        return this;
    }

    @Step("Select currency: \"{0}\" from 'popular list'")
    public ConverterToolPage chooseCurrencyFromPopularList(String popularCurrencyShortProps) {
        getCurrenciesFromPopularList().find(matchesText(popularCurrencyShortProps)).click();
        return this;
    }

    @Step("Select currency: \"{0}\" from 'common list'")
    public ConverterToolPage setCurrencyFromCommonList(String converterCurrenciesValue) {
        getCurrenciesFromCommonList().find(matchesText(converterCurrenciesValue)).click();
        return this;
    }

    public SelenideElement converterTabCurrencyName() {
        return $(".converter-tabCurrencyName");
    }

    public SelenideElement converterPopularItemCurrencyName() {
        return $(".converter-popularItem__selected");
    }

    private SelenideElement converterCurrenciesSelectedItemFromCommonList() {
        return $(".converter-currenciesItem__selected");
    }

    public SelenideElement converterCurrencySymbolFromCommonList() {
        return converterCurrenciesSelectedItemFromCommonList().$(".converter-currenciesSymbol");
    }

    public SelenideElement converterCurrencyNameFromCommonList() {
        return converterCurrenciesSelectedItemFromCommonList().$(".converter-currenciesName");
    }

    @Step("Clear converter tabs")
    public ConverterToolPage cleanConverterTabByButton() {
        $(".ui-btnTxt").shouldHave(matchesText("Очистить")).click();
        return this;
    }

    @Step("Find currency \"{0}\"")
    public ConverterToolPage findCurrencyFor(String theCurrencyShortProps) {
        $("#find-currencies").val(theCurrencyShortProps);
        return this;
    }

    @Step("Select currencies from search result")
    public ConverterToolPage chooseCurrencyFromSearchResult() {
        getCurrenciesFromCommonList().get(0).click();
        return this;
    }

    public SelenideElement getTextNothingFound() {
        return $$(".converter-container").get(1).$(".txt-h3");
    }

}
