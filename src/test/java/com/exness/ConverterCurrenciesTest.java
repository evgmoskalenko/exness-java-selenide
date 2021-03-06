package com.exness;

import com.exness.app.internal.BrowserConsole;
import com.exness.app.internal.PageObjectsSupplier;
import com.exness.app.wrappers.BaseTest;
import com.exness.pages.tools.ConverterToolPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.exness.model.Currencies.*;
import static com.exness.pages.tools.ConverterToolPage.CurrenciesTabs.CURR_TAB_FROM;
import static com.exness.pages.tools.ConverterToolPage.CurrenciesTabs.CURR_TAB_TO;


@Feature("Converter")
@Story("Currencies converter")
public class ConverterCurrenciesTest extends BaseTest implements PageObjectsSupplier, BrowserConsole {

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "convert_currencies", priority = 10)
    public void userShouldBeAbleToConvertCurrency() {
        // --------------------- Test Data ----------------------//
        double currencyBid = 1.28914;
        double currencyAsk = 1.28966;

        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .findCurrencyFor(GBP.getShortProps())
                    .chooseCurrencyFromSearchResult()
                    .setTabCurrencyValue("150");

        double actualResult = results
                .chooseCurrencyConverterTab(CURR_TAB_TO)
                    .findCurrencyFor(USD.getShortProps())
                    .chooseCurrencyFromSearchResult()
                    .getTabCurrencyValue();

        double expectedResult = results
                .getConvertedCurrencyValue(150, currencyBid, currencyAsk);

        verifyEquals(actualResult, expectedResult);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "convert_currencies", priority = 15)
    public void currencyRoundingOff() {
        // --------------------- Test Data ----------------------//
        double currencyBid = 1.28914;
        double currencyAsk = 1.28966;

        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage();

        double actualResult = results
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .findCurrencyFor(GBP.getShortProps())
                    .chooseCurrencyFromSearchResult()
                    .setTabCurrencyValue("1")
                .chooseCurrencyConverterTab(CURR_TAB_TO)
                    .findCurrencyFor(USD.getShortProps())
                    .chooseCurrencyFromSearchResult()
                .getTabCurrencyValue();

        double expectedResult = results
                .getConvertedCurrencyValue(1, currencyBid, currencyAsk);

        verifyEquals(actualResult, expectedResult);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "convert_currencies", priority = 20)
    public void userShouldBeAbleToConvertCurrencyWithDoubleValue() {
        // --------------------- Test Data ----------------------//
        double currencyBid = 1.12816;
        double currencyAsk = 1.12836;

        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .chooseCurrencyFromPopularList(EUR.getShortProps())
                    .setTabCurrencyValue("150.500");

        double actualResult = results
                .chooseCurrencyConverterTab(CURR_TAB_TO)
                    .chooseCurrencyFromPopularList(USD.getShortProps())
                    .getTabCurrencyValue();

        double expectedResult = results
                .getConvertedCurrencyValue(150.500, currencyBid, currencyAsk);

        verifyEquals(actualResult, expectedResult);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = "convert_currencies", priority = 25)
    public void userShouldBeAbleToConvertCurrencyWithSemicolonValue() {
        // --------------------- Test Data ----------------------//
        double currencyBid = 1.28914;
        double currencyAsk = 1.28966;

        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage();

        double actualResult = results
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .findCurrencyFor(GBP.getShortProps())
                    .chooseCurrencyFromSearchResult()
                    .setTabCurrencyValue("15,55")
                .chooseCurrencyConverterTab(CURR_TAB_TO)
                    .findCurrencyFor(USD.getShortProps())
                    .chooseCurrencyFromSearchResult()
                .getTabCurrencyValue();

        double expectedResult = results
                .getConvertedCurrencyValue(15.55, currencyBid, currencyAsk);

        verifyEquals(actualResult, expectedResult);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "selecting_currencies", priority = 30)
    public void userShouldBeAbleToChooseCurrenciesFromPopularList() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .chooseCurrencyFromPopularList(AUD.getShortProps());

        verifyEquals(results.converterTabCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterPopularItemCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencySymbolFromCommonList().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencyNameFromCommonList().text(), AUD.getLongProps());
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "selecting_currencies", priority = 40)
    public void userShouldBeAbleToChooseCurrenciesFromCommonList() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .setCurrencyFromCommonList(AUD.getLongProps());

        verifyEquals(results.converterTabCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterPopularItemCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencySymbolFromCommonList().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencyNameFromCommonList().text(), AUD.getLongProps());
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "clear_converter_tabs", priority = 50)
    public void userShouldBeAbleToCleanConverterTabs() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .chooseCurrencyFromPopularList(AUD.getShortProps())
                    .setTabCurrencyValue("15.55")
                .chooseCurrencyConverterTab(CURR_TAB_TO)
                    .chooseCurrencyFromPopularList(EUR.getShortProps())
                .cleanConverterTabByButton();

        verifyEquals(results.chooseCurrencyConverterTab(CURR_TAB_FROM).getTabCurrencyValue(), 0);
        verifyEquals(results.chooseCurrencyConverterTab(CURR_TAB_TO).getTabCurrencyValue(), 0);

    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "find_currencies", priority = 60)
    public void userShouldBeAbleToFindPopularCurrencyByShortProps() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .setTabCurrencyValue("125")
                    .findCurrencyFor(AUD.getShortProps())
                .chooseCurrencyFromSearchResult();

        results.getCurrenciesFromCommonList().shouldHaveSize(1);
        results.getCurrenciesFromPopularList().shouldHaveSize(1);

        verifyEquals(results.converterPopularItemCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterTabCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencySymbolFromCommonList().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencyNameFromCommonList().text(), AUD.getLongProps());
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "find_currencies", priority = 70)
    public void userShouldBeAbleToFindCurrencyByLongProps() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .setTabCurrencyValue("125")
                    .findCurrencyFor(AUD.getLongProps())
                    .chooseCurrencyFromSearchResult();

        results.getCurrenciesFromCommonList().shouldHaveSize(1);
        //results.getCurrenciesFromPopularList().shouldHaveSize(1);

        //TODO: При поиске "популярной валюты" по "длинному названию (Австралийский доллар)" в списке "Популярные валюты" - не отображается короткое название валюты ("AUD")
        //verifyEquals(results.converterPopularItemCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterTabCurrencyName().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencySymbolFromCommonList().text(), AUD.getShortProps());
        verifyEquals(results.converterCurrencyNameFromCommonList().text(), AUD.getLongProps());
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "find_currencies", priority = 80)
    public void userShouldNotFindCurrencyWhichIsNotInTheList() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                    .findCurrencyFor("USH");

        results.getCurrenciesFromCommonList().shouldHaveSize(0);
        results.getCurrenciesFromPopularList().shouldHaveSize(0);

        verifyEquals(results.getTextNothingFound().text(), "Nothing found");
    }

}
