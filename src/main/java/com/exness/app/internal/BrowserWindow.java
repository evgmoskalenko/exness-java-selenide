package com.exness.app.internal;

import static com.codeborne.selenide.Selenide.switchTo;

public interface BrowserWindow {

    default void switchToSecondTab() {
        switchTo().window(1);
    }

    default void switchToTabByIndex(int index) {
        switchTo().window(index);
    }

    default void switchToTabByName(String nameOrHandleOrTitle) {
        switchTo().window(nameOrHandleOrTitle);
    }

    default void switchToDefaultTab() {
        switchTo().defaultContent();
    }

}
