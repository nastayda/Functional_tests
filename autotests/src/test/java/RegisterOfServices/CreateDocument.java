package RegisterOfServices;

import HelpClasses.BaseClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import ru.yandex.qatools.allure.annotations.Title;
import java.time.LocalDateTime;
import static org.testng.Assert.assertEquals;

public class CreateDocument extends BaseClass {

    @Test
    @Title("Создание дела")
    public void CreateDocument() throws Exception {
        login("userName", "password", "admin", "admin");
        //Тестовое имя объекта
        String nameObject = "Test"+LocalDateTime.now().toString().replace(":","_");
        createNewDocument(nameObject);
        //Сравниваем имя созданного дела и того которое добавилось
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[5]")).getText(),nameObject );
    }
    public void createNewDocument(String nameObject) {
        //Переход на страницу с делами - иногда не работает
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        //Ждем пока загрузится кнопка Добавить
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div/div[1]/div/div/div/div[2]/div[2]/div[1]/button")));
        wd.findElement(By.cssSelector("button.ant-btn.ant-btn-lg")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-footer']//button[2]")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();
        wd.findElement(By.id("object_name")).sendKeys(nameObject);
        wd.findElement(By.xpath("//div[3]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
    }
}
