package com.exness.app.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.sleep;
import static com.exness.app.wrappers.BaseTest.getDriver;

public interface Cookies {

    Logger logger = LogManager.getLogger(Cookies.class);

    default void clearCookies() {
        getDriver().manage().deleteAllCookies();
        getDriver().manage().deleteAllCookies();
        sleep(300);
    }

    default void clearCookies(String urlIgnoring) {
        if (!(getDriver().getCurrentUrl().equalsIgnoreCase(urlIgnoring))) {
            getDriver().manage().deleteAllCookies();
            getDriver().manage().deleteAllCookies();
            sleep(300);
        }
    }

    @Step("Add cookies for page with name = \"{0}\", value = \"{1}\"")
    default void addCookies(String cookiesName, String cookiesValue) {
        Cookie ck = new Cookie(cookiesName, cookiesValue);
        getDriver().manage().addCookie(ck);
        logger.info(String.format("add cookies with name = \"%s\", value = \"%s\"", cookiesName, cookiesValue));
    }

    /**
     * Deleting the specific cookie with cookie name
     * @param cookieName Cookie name "--utmb"
     */
    @Step("Deleting the specific cookie with cookie name \"{0}\"")
    default void deleteCookieNamed(final String cookieName) {
        getDriver().manage().deleteCookieNamed(cookieName);
    }


}
