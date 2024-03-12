package ru.skillbox.patterns.facade.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Tabbar extends Screen {

    @AndroidFindBy(accessibility = "Каталог")
    MobileElement catalogElement;

    public Tabbar(AndroidDriver<?> driver) {
        super(driver);
    }

    public boolean isCatalogSelected() {
        return catalogElement.isSelected();
    }

    public CatalogScreen clickCatalog() {
        catalogElement.click();
        return new CatalogScreen(driver);
    }
}
