package com.exness.app.internal;

import com.exness.app.wrappers.BasePage;

import static com.exness.app.wrappers.BaseTest.getPages;

public interface GenericPage {

    static BasePage getPageObject(final GenericPage page) {
        getPages().putIfAbsent(page, page.create());
        return getPages().get(page);
    }

    BasePage create();
}
