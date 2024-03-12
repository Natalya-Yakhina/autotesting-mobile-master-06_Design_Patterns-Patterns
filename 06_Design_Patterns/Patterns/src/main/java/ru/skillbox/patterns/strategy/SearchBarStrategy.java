package ru.skillbox.patterns.strategy;

import io.appium.java_client.AppiumDriver;
import ru.skillbox.patterns.strategy.screens.CatalogScreen;

public class SearchBarStrategy implements SearchStrategy {
    AppiumDriver driver;

    public SearchBarStrategy(AppiumDriver driver) {
        this.driver = driver;
    }

    @Override
    public void search() {
        CatalogScreen catalogScreen = new CatalogScreen(driver);
        catalogScreen.search("Телевизор");
    }
}
