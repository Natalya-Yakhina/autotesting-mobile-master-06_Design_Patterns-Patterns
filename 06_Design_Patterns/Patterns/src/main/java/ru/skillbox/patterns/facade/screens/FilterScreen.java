package ru.skillbox.patterns.facade.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FilterScreen extends Tabbar {

    @AndroidFindBy(id = "filter_switch")
    MobileElement filterSwitch;

    @AndroidFindBy(id = "apply_filters")
    MobileElement applyFiltersButton;

    public FilterScreen(AndroidDriver<?> driver) {
        super(driver);
    }

    public FilterScreen turnOnDiscountedGoods() {
        filterSwitch.click();
        return this;
    }

    public CatalogScreen applyFilters() {
        applyFiltersButton.click();
        return new CatalogScreen(driver);
    }
}
