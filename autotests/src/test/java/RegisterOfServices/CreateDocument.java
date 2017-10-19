package RegisterOfServices;

import HelpClasses.BaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class CreateDocument extends BaseClass {

    @Test
    public void CreateDocument() {
        wd.get("http://vm-107-stu-dev.ursip.ru/auth/");
        wd.findElement(By.id("userName")).click();
        wd.findElement(By.id("userName")).clear();
        wd.findElement(By.id("userName")).sendKeys("admin");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("admin");
        wd.findElement(By.id("authorization")).click();
        wd.findElement(By.xpath("//div[@class='authorization-page']//button[.='Войти']")).click();
        wd.findElement(By.cssSelector("button.ant-btn.ant-btn-lg")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-footer']//button[.='Создать']")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();
        wd.findElement(By.id("object_name")).sendKeys("Test");
        wd.findElement(By.xpath("//div[3]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
    }
}
