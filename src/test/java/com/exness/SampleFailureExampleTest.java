package com.exness;

import com.exness.app.internal.PageObjectsSupplier;
import com.exness.app.wrappers.BaseTest;
import com.exness.pages.tools.ConverterToolPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.exness.pages.tools.ConverterToolPage.CurrenciesTabs.CURR_TAB_FROM;

@Feature("Currencies converter")
@Story("Sample failure tests for example. Allure report..")
public class SampleFailureExampleTest extends BaseTest implements PageObjectsSupplier {

    @Test(groups = "failure_currencies_tests", priority = 10)
    public void failureTestUserShouldCanChooseCurrenciesFromPopularList() {
        // --------------------- Test Case ----------------------//
        ConverterToolPage results = converterToolPage().openPage()
                .chooseCurrencyConverterTab(CURR_TAB_FROM)
                .chooseCurrencyFromPopularList("AUD");

        verifyEquals(results.converterTabCurrencyName().text(), "111AUD");
        verifyEquals(results.converterPopularItemCurrencyName().text(), "222AUD");
        verifyEquals(results.converterCurrencySymbolFromCommonList().text(), "333AUD");
        verifyEquals(results.converterCurrencyNameFromCommonList().text(), "444Австралийский доллар");
    }

}