package com.exness.app.wrappers;

import com.exness.app.internal.BrowserWindow;
import com.exness.app.internal.Cookies;
import com.exness.app.internal.PageObjectsSupplier;
import com.exness.app.internal.Pages;
import com.exness.app.utils.properties.SystemProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage<T> implements PageObjectsSupplier, Pages, BrowserWindow, Cookies {

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    protected static final String baseUrl = SystemProperty.BASE_URL.getValue();

    abstract protected String getUrl();

    public T openPage() {
        open(getUrl());
        return get();
    }

    public T openPage(String customUrlPage) {
        open(customUrlPage);
        return get();
    }

    public T openPageWithTab(String navTab) {
        open(getUrl() + navTab);
        return get();
    }

    public T openPageWithTab(String customUrlPage, String navTab) {
        open(customUrlPage + navTab);
        return get();
    }

    @SuppressWarnings("unchecked")
    private T get() {
        return (T) this;
    }

//    @SuppressWarnings("unchecked")
//    public T thenGoTo(Class<T> clazz) {
//        return init(clazz);
//    }
//
//    @SuppressWarnings("unchecked")
//    public T thenGoToWithUrl(Class<T> clazz, String url) {
//        return init(clazz, url);
//    }

}
