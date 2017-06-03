package com.exness.app.internal;

import com.exness.app.wrappers.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.exness.app.utils.report.AllureReport.htmlToAllureReport;
import static com.exness.app.utils.report.AllureReport.textToAllureAsStep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public interface Validator {

    Logger logger = LogManager.getLogger(BaseTest.class);

    @Step("Verify characters count in string")
    default void verifyStringCount(String s, int expectedLength) {
        int actualLength = s.length();
        if (actualLength <= expectedLength) {
            logger.info("Current characters: " +
                    actualLength + ", and expected length: " + expectedLength);
        } else {
            throw new ExceptionInInitializerError("Current characters: " +
                    actualLength + ", and expected length: " + expectedLength);
        }
    }

    @Step("Verify browser console log entry")
    default void verifyBrowserConsoleLogEntry(List<String> listBrowserConsoleLog) {
        boolean condition = listBrowserConsoleLog.isEmpty();
        if (!condition) {
            htmlToAllureReport("Browser console log entry", String.join("<br><br>", listBrowserConsoleLog));
        }
        assertTrue(condition);
    }

    @Step("Verify that \"{1}\" = \"{0}\"")
    default void verifyEquals(final String actual, final String expected, final String message) {
        textToAllureAsStep("Actual", actual);
        textToAllureAsStep("Expected", expected);
        textToAllureAsStep("Message", message);
        assertEquals(actual, expected, message);
    }

    @Step("Verify that \"{1}\" = \"{0}\"")
    default void verifyEquals(final String actual, final String expected) {
        textToAllureAsStep("Actual", actual);
        textToAllureAsStep("Expected", expected);
        assertEquals(actual, expected);
    }

    @Step("Verify that \"{1}\" = \"{0}\"")
    default void verifyEquals(final double actual, final double expected) {
        textToAllureAsStep("Actual", actual);
        textToAllureAsStep("Expected", expected);
        assertEquals(actual, expected);
    }

    @Step("Verify that \"{0}\" contains \"{1}\"")
    default void verifyTextContains(final String actual, final String containsString) {
        htmlToAllureReport("Actual: "+actual+"<br><br>Contains string: "+containsString);
        assertTrue(actual.contains(containsString));
    }

    @Step("Verify that \"{0}\" not contains \"{1}\"")
    default void verifyTextNotContains(final String actual, final String containsString) {
        assertTrue(!actual.contains(containsString));
    }

    @Step("Verify that url \"{0}\" contains \"{1}\"")
    default void verifyUrlContains(final String actual, final String containsString) {
        textToAllureAsStep("Actual", actual);
        textToAllureAsStep("Expected", containsString);
        assertTrue(actual.contains(containsString));
    }

}

