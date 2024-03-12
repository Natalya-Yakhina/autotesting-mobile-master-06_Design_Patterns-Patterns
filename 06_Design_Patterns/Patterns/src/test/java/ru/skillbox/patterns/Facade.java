package ru.skillbox.patterns;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.skillbox.patterns.facade.DriverFactory;
import ru.skillbox.patterns.facade.screens.CatalogScreen;
import ru.skillbox.patterns.facade.screens.SearchDiscountGoodsFacade;
import ru.skillbox.patterns.facade.screens.Tabbar;
import ru.skillbox.patterns.facade.action.Direction;
import ru.skillbox.patterns.facade.action.SwipeHelper;

import java.net.MalformedURLException;
import java.time.Duration;

public class Facade {

    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before
    @Step("Настройка драйвера")
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
    }

    @Test
    public void facade() {
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
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //сохранение в переменной foundTitleText текста “Найдено ...телевизоров”
        String foundTitleText = catalogScreen.getFoundTitleText();
        //переход в Фильтры.
        //активация переключателя “Товары со скидкой” и нажатие на красную кнопку внизу “Показать..”.
        SearchDiscountGoodsFacade searchDiscountGoodsFacade = new SearchDiscountGoodsFacade(driver);
        searchDiscountGoodsFacade.searchDiscountGoods();
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