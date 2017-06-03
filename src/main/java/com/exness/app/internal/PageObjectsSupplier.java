package com.exness.app.internal;

import com.exness.app.wrappers.BasePage;
import com.exness.pages.tools.ConverterToolPage;
import com.exness.pages.HomePage;

import static com.exness.app.internal.GenericPage.getPageObject;
import static com.exness.app.internal.PageObjectsSupplier.PageObject.CONVERTER_TOOL_PAGE;
import static com.exness.app.internal.PageObjectsSupplier.PageObject.HOME_PAGE;

public interface PageObjectsSupplier<T extends PageObjectsSupplier<T>> {

    enum PageObject implements GenericPage {

        HOME_PAGE {
            public BasePage create() {
                return new HomePage();
            }
        },
        CONVERTER_TOOL_PAGE {
            public BasePage create() {
                return new ConverterToolPage();
            }
        };

    }

    default HomePage homePage() {
        return (HomePage) getPageObject(HOME_PAGE);
    }

    default ConverterToolPage converterToolPage() {
        return (ConverterToolPage) getPageObject(CONVERTER_TOOL_PAGE);
    }

}
