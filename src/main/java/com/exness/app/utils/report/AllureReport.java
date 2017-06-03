package com.exness.app.utils.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class AllureReport {

    private static final Logger logger = LogManager.getLogger(AllureReport.class);

    //TODO: Add JavaDOC for methods

    @Attachment(value = "HTML log in table", type = "text/html")
    public static String htmlLogInTableToAllureReport(String text) {
        return text;
    }

    @Attachment(value = "HTML attachments", type = "text/html")
    public static String htmlToAllureReport(String html) {
        return html;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String htmlToAllureReport(String attachName, String html) {
        return html;
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String codeToAllureReport(String attachName, String html) {
        return html;
    }

    @Attachment(value = "Text attachments", type = "text/txt")
    public static String textToAllureReport(String text) {
        return text;
    }

    @Attachment(value = "{0}", type = "text/txt")
    public static String textToAllureReport(String attachName, String text) {
        return text;
    }

    @Step("{0}: {1}")
    public static String textToAllureAsStep(String result, String text) {
        return text;
    }

    @Step("{0}: {1}")
    public static double textToAllureAsStep(String result, double value) {
        return value;
    }

    @Step("{0}: {1}")
    public static boolean textToAllureAsStep(String result, boolean condition) {
        return condition;
    }

    @Step("{0}: {1}")
    public static int textToAllureAsStep(String result, int intValue) {
        return intValue;
    }

}
