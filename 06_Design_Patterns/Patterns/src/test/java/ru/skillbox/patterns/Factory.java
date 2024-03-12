package ru.skillbox.patterns;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.skillbox.patterns.factory.DriverFactory;
import ru.skillbox.patterns.factory.Platform;
import ru.skillbox.patterns.factory.action.Direction;
import ru.skillbox.patterns.factory.action.SwipeHelper;
import ru.skillbox.patterns.factory.screens.CatalogScreen;
import ru.skillbox.patterns.factory.screens.FilterScreen;
import ru.skillbox.patterns.factory.screens.Tabbar;

import java.net.MalformedURLException;
import java.time.Duration;

public class Factory {
    private final DriverFactory driverFactory = new DriverFactory();
    private AppiumDriver<?> driver;

    @Before
    @Step("Настройка драйвера")
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp(Platform.ANDROID);
    }

    @Test
    public void factory() {
        Tabbar tabbar = new Tabbar(driver);
        //проверка, что вкладка “Каталог” в нижнем таббаре не выбрана
        Assert.assertFalse(tabbar.isCatalogSelected());
        //переход в таббар на вкладку Каталог
        tabbar.clickCatalog();
        //проверка, что теперь вкладка “Каталог” выбрана
        Assert.assertTrue(tabbar.isCatalogSelected());
        //одинарный скролл вверх и одинарный скролл вниз
        SwipeHelper swipeHelper = new SwipeHelper(driver);
        swipeHelper.swipe(Direction.UP);
        swipeHelper.swipe(Direction.DOWN);
        //в поле “Название товара” ввод “Телевизор” и переход к результатам поиска.
        CatalogScreen catalogScreen = new CatalogScreen(driver);
        String television = "Телевизор";
        catalogScreen.search(television);
        ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        //сохранение в переменной foundTitleText текста “Найдено ...телевизоров”
        String foundTitleText = catalogScreen.getFoundTitleText();
        //переход в Фильтры.
        catalogScreen.clickFilters();
        //активация переключателя “Товары со скидкой” и нажатие на красную кнопку внизу “Показать..”.
        FilterScreen filterScreen = new FilterScreen(driver);
        filterScreen.turnOnDiscountedGoods();
        filterScreen.applyFilters();
        //сохранение в переменную countSaleTitle текста “Найдено ...телевизоров”.
        String foundSaleTitleText = catalogScreen.getFoundTitleText();
        //проверка, что переменная countTitle не равна countSaleTitle.
        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);
        //блокировка экрана эмулятора на 3 секунды.
        ((AndroidDriver<?>) driver).lockDevice(Duration.ofSeconds(3));
        //проверка, что после разблокировки экрана в поисковом поле остался текст “Телевизор”.
        Assert.assertEquals(television, catalogScreen.getSearchText());
        //свайп влево/вправо не по всему экрану, а по виджету “Уточните категорию”
        catalogScreen.swipeSpecifyCategoryPager();
    }
}
