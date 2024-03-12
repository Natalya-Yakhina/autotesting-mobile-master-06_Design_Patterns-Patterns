package ru.skillbox.patterns.singleton;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.NO_RESET;

public class SingletonAppiumDriver {

    private static AppiumDriver<?> driver;

    private SingletonAppiumDriver() {
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability(APP_PACKAGE, "ru.filit.mvideo.b2c");
        capabilities.setCapability(APP_ACTIVITY, ".ui.splash.view.SplashScreen");
        capabilities.setCapability(NO_RESET, true);
        return capabilities;
    }

    public static AppiumDriver<?> getDriver() throws MalformedURLException {
        if (driver == null) {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), getDesiredCapabilities());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
}
