package ru.skillbox.patterns.singleton.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import ru.skillbox.patterns.singleton.action.Direction;

public class CatalogScreen extends Tabbar {

    @AndroidFindBy(id = "search_title")
    MobileElement searchTitle;

    @AndroidFindBy(id = "search_src_text")
    MobileElement searchText;

    @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*id/title\").textContains(\"товаров\")")
    MobileElement foundTitle;

    @AndroidFindBy(id = "filter_label")
    MobileElement filterLabel;

    @AndroidFindBy(id = "specify_category_pager")
    MobileElement specifyCategoryPager;

    public CatalogScreen(AppiumDriver<?> driver) {
        super(driver);
    }

    public CatalogScreen swipeSpecifyCategoryPager() {
        swipeHelper.swipe(Direction.LEFT, specifyCategoryPager);
        return this;
    }

    public String getFoundTitleText() {
        return foundTitle.getText();
    }

    public CatalogScreen search(String text) {
        searchTitle.click();
        searchText.sendKeys(text);
        return this;
    }

    public FilterScreen clickFilters() {
        filterLabel.click();
        return new FilterScreen(driver);
    }

    public String getSearchText() {
        return searchText.getText();
    }
}
