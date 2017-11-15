package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.OnOffNetAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OffInternetAndCheck extends BaseClass {
    SoftAssert softAssert = new SoftAssert( );

    @Test
    @Title("Проверка отображения эелемнтов после отключения интернета")
    public void test( ) throws NoSuchMethodException {
        OnOffNetAdapter state = new OnOffNetAdapter( );
        wd.navigate( ).refresh( );
        login( );
        state.onOffInternet( "disable" );
        clickToMenu( );
        state.onOffInternet( "enable" );
        softAssert.assertAll( );
    }

    @Step("Преход по элементам меню")
    public void clickToMenu( ) {
        List<WebElement> el = wd.findElements( By.xpath( "//*" ) );
        System.out.println( el.size( ) );
        softAssert.assertTrue( el.size( ) > 50, "Переход по пунктам меню провален" );
        if (el.size( ) > 50) {
            for (int i = 1; i < 9; i++) {
                wd.findElement( By.xpath( "//ul/li[" + i + "]/div/div[2]" ) ).click( );
            }
        }
    }
}