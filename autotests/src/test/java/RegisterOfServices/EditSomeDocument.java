package RegisterOfServices;

import HelpClasses.BaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class EditSomeDocument extends BaseClass {

    @Test
    public void EditDocument() {
        login("userName", "password", "admin", "admin");
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        wd.findElement(By.cssSelector("div.departments-tree")).click();
        //озьмем последнюю строку в таблице для редактирования
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]")).click();
        wd.findElement(By.id("change")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();
        //Генерим случайное название объекта "Test"+дата
        String nameObject = "Test"+LocalDateTime.now().toString().replace(":","_");
        //Заполняем в форме имя объекта
        wd.findElement(By.id("object_name")).sendKeys(nameObject);
        wd.findElement(By.xpath("//html/body/div[2]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
        //Сравним измененный текст объекта с тем что есть в таблице
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[5]")).getText(),nameObject);
    }
}
