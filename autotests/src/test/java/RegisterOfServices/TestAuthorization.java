package RegisterOfServices;

import HelpClasses.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.Assert.assertEquals;

public class TestAuthorization extends BaseClass {

    @Title("Авторизация пользователя")
    @Test
   // @Step("Вызов метода авторизации")
    public void TestAuthorization() {
      login("userName", "password", "admin", "admin");
        boolean flag = wd.findElement(By.xpath("//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div/span[2]/button")).isEnabled();
        assertEquals(flag, true);
    }

}
