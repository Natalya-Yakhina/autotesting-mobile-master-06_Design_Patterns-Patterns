package ru.skillbox.patterns.screen_object.screens;

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

    public void turnOnDiscountedGoods() {
        filterSwitch.click();
    }

    public void applyFilters() {
        applyFiltersButton.click();
    }
}
