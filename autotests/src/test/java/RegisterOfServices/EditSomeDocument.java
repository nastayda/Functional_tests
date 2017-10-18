package RegisterOfServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class EditSomeDocument {
    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @Test
    public void EditDocument() {
        //////////
        wd.get("http://vm-107-stu-dev.ursip.ru/auth/");
        wd.findElement(By.id("userName")).click();
        wd.findElement(By.id("userName")).clear();
        wd.findElement(By.id("userName")).sendKeys("admin");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("admin");
        wd.findElement(By.xpath("//div[@class='authorization-page']//button[.='Войти']")).click();
        ///////////
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        wd.findElement(By.cssSelector("div.departments-tree")).click();
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[16]")).click();
        //wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='Test11']")).click();
        wd.findElement(By.id("change")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();

        //Генерим случайное название объекта
        Random random = new Random();
        int num = random.nextInt(5);
        String nameObject = "Test"+num;
        //Заполняем в форме имя объекта
        wd.findElement(By.id("object_name")).sendKeys(nameObject);
        wd.findElement(By.xpath("//div[@class='ant-modal-footer']//button[.=' Сохранить ']")).click();
        //////////////
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[16]/td[5]")).getText(),nameObject);
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
