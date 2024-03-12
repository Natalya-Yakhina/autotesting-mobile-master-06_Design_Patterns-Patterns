package ru.skillbox.patterns.singleton.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.skillbox.patterns.singleton.action.SwipeHelper;

public class Screen {

    AppiumDriver<?> driver;
    SwipeHelper swipeHelper;

    Screen(AppiumDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        swipeHelper = new SwipeHelper(driver);
    }

}
