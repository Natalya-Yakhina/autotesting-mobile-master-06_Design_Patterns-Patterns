package ru.skillbox.patterns.singleton.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.net.MalformedURLException;

public class Tabbar extends Screen {

    @AndroidFindBy(accessibility = "Каталог")
    MobileElement catalogElement;

    public Tabbar(AppiumDriver<?> driver) {
        super(driver);
    }

    public boolean isCatalogSelected() {
        return catalogElement.isSelected();
    }

    public CatalogScreen clickCatalog() throws MalformedURLException {
        catalogElement.click();
        return new CatalogScreen(driver);
    }
}
