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
    @Step("Вызов метода авторизации")
    public void TestAuthorization() {
        login("userName", "password", "admin", "admin");
      //Завязываемся на отображение таблицы
        boolean flag = wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table")).isEnabled();
        assertEquals(flag, true);
    }

}
