package RegisterOfServices;

import HelpClasses.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.io.IOException;

public class OffInternetAndCheck extends BaseClass {
    @Test
    @Title( "Проверка отображения эелемнтов после отключения интернета" )
    public void test( ) throws NoSuchMethodException {
        login( "userName", "password", "admin", "admin" );
        onOffInternet( "disable" );
        clickToMenu( );
        onOffInternet( "enable" );
        clickToMenu( );
    }

    @Step("Преход по элементам меню")
    public void clickToMenu( ) {
        wd.findElement( By.cssSelector( "div.ant-col-22" ) ).click( );
        for (int i = 1; i < 9; i++) {
            wd.findElement( By.xpath( "//div[@class='departments-tree']/div/ul/li[" + i + "]/div/div[2]" ) ).click( );
        }
    }

    @Step("Сетевой адаптер {0}")
    public void onOffInternet( final String stateNetworkAdapter ) throws NoSuchMethodException {
        File testFile = new File( "src/help-files/offinternet.bat" );
        try {
            String command = "cmd /c start cmd.exe /k " + testFile.getAbsolutePath( ) + " " + stateNetworkAdapter;
            Runtime.getRuntime( ).exec( command );
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }
}