package ru.skillbox.patterns.facade.screens;

import io.appium.java_client.android.AndroidDriver;

public class SearchDiscountGoodsFacade extends Screen {

    CatalogScreen catalogScreen;
    FilterScreen filterScreen;

    public SearchDiscountGoodsFacade(AndroidDriver<?> driver) {
        super(driver);
    }

    public CatalogScreen getCatalogScreen() {
        if (catalogScreen == null) {
            catalogScreen = new CatalogScreen(driver);
        }
        return catalogScreen;
    }

    public FilterScreen getFilterScreen() {
        if (filterScreen == null) {
            filterScreen = new FilterScreen(driver);
        }
        return filterScreen;
    }

    public void searchDiscountGoods() {
        getCatalogScreen().clickFilters();
        getFilterScreen().turnOnDiscountedGoods().applyFilters();
    }
}
