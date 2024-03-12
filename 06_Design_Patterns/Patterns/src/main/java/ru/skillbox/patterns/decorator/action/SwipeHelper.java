package ru.skillbox.patterns.decorator.action;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class SwipeHelper {

    AndroidDriver<?> driver;

    public SwipeHelper(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    public void swipe(Direction direction) {
        Dimension dims = driver.manage().window().getSize();
        int width = dims.width / 2;
        int height = dims.height / 2;
        swipeExecution(direction, width, height);
    }

    public void swipe(Direction direction, MobileElement element) {
        int width = element.getCenter().x;
        int height = element.getCenter().y;
        swipeExecution(direction, width, height);
    }

    private void swipeExecution(Direction direction, int width, int height) {
        int border = 5;
        int press = 300;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(width, height);
        PointOption<?> pointOptionEnd;
        switch (direction) {
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - border, height);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(border, height);
                break;
            case UP:
                pointOptionEnd = PointOption.point(width, border);
                break;
            case DOWN:
                pointOptionEnd = PointOption.point(width, dims.height - border);
                break;
            default:
                throw new IllegalArgumentException("swipe is NOT supported");
        }

        new TouchAction<>(driver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(press)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();
    }
}
