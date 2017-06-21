package com.exness.app.internal;

import com.exness.app.wrappers.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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

    //TODO: refactoring this impl
    @Step("Verify browser console log entry")
    default void verifyBrowserConsoleLogEntry(List<String> listBrowserConsoleLog) {
        boolean condition = listBrowserConsoleLog.isEmpty();
        if (!condition) {
            Allure.addAttachment("Browser console log entry", "text/html",
                                 String.join("<br><br>", listBrowserConsoleLog));
        }
        assertTrue(condition);
    }

    @Step("Verify that \"{expected}\" = \"{actual}\"")
    default void verifyEquals(final String actual, final String expected, final String message) {
        assertEquals(actual, expected, message);
    }

    @Step("Verify that \"{expected}\" = \"{actual}\"")
    default void verifyEquals(final String actual, final String expected) {
        assertEquals(actual, expected);
    }

    @Step("Verify that \"{expected}\" = \"{actual}\"")
    default void verifyEquals(final double actual, final double expected) {
        assertEquals(actual, expected);
    }

    //TODO: refactoring this impl
    @Step("Verify that \"{actual}\" contains \"{containsString}\"")
    default void verifyTextContains(final String actual, final String containsString) {
        Allure.addAttachment("HTML attachments", "text/html",
                             "Actual: " + actual + "<br><br>Contains string: " + containsString);
        assertTrue(actual.contains(containsString));
    }

    @Step("Verify that \"{actual}\" not contains \"{containsString}\"")
    default void verifyTextNotContains(final String actual, final String containsString) {
        assertTrue(!actual.contains(containsString));
    }

    @Step("Verify that url \"{actual}\" contains \"{containsString}\"")
    default void verifyUrlContains(final String actual, final String containsString) {
        assertTrue(actual.contains(containsString));
    }

}

