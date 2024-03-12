package ru.skillbox.patterns;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.skillbox.patterns.chain_of_invocations.DriverFactory;
import ru.skillbox.patterns.chain_of_invocations.screens.CatalogScreen;
import ru.skillbox.patterns.chain_of_invocations.screens.Tabbar;

import java.net.MalformedURLException;
import java.time.Duration;

public class ChainOfInvocations {
    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before
    @Step("Настройка драйвера")
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
    }

    @Test
    public void chOfInv() {
        Tabbar tabbar = new Tabbar(driver);
        CatalogScreen catalogScreen = new CatalogScreen(driver);
        //проверка, что вкладка “Каталог” в нижнем таббаре не выбрана
        Assert.assertFalse(tabbar.isCatalogSelected());
        //переход в таббар на вкладку Каталог
        //в поле “Название товара” ввод “Телевизор” и переход к результатам поиска.
        String television = "Телевизор";
        tabbar.clickCatalog().search("Телевизор");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //сохранение в переменной foundTitleText текста “Найдено ...телевизоров”
        String foundTitleText = catalogScreen.getFoundTitleText();
        //переход в Фильтры.
        //активация переключателя “Товары со скидкой” и нажатие на красную кнопку внизу “Показать..”.
        catalogScreen.clickFilters().turnOnDiscountedGoods().applyFilters();
        //сохранение в переменную countSaleTitle текста “Найдено ...телевизоров”.
        String foundSaleTitleText = catalogScreen.getFoundTitleText();
        //проверка, что переменная countTitle не равна countSaleTitle.
        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);
        //блокировка экрана эмулятора на 3 секунды.
        driver.lockDevice(Duration.ofSeconds(3));
        //проверка, что после разблокировки экрана в поисковом поле остался текст “Телевизор”.
        Assert.assertEquals(television, catalogScreen.getSearchText());
        //свайп влево/вправо не по всему экрану, а по виджету “Уточните категорию”
        catalogScreen.swipeSpecifyCategoryPager();
    }
}