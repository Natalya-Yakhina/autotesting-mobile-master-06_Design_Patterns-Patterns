package ru.skillbox.patterns.decorator.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.skillbox.patterns.decorator.action.SwipeHelper;

public class Screen {

    AndroidDriver<?> driver;
    SwipeHelper swipeHelper;

    Screen(AndroidDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        swipeHelper = new SwipeHelper(driver);
    }

}
