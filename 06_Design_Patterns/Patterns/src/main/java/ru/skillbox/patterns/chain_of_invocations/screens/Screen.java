package ru.skillbox.patterns.chain_of_invocations.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.skillbox.patterns.chain_of_invocations.action.SwipeHelper;

public class Screen {

    AndroidDriver<?> driver;
    SwipeHelper swipeHelper;

    Screen(AndroidDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        swipeHelper = new SwipeHelper(driver);
    }

}
