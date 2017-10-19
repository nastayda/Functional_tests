package RegisterOfServices;

import HelpClasses.BaseClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import org.openqa.selenium.*;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreateDocument extends BaseClass {

    @Test
    public void CreateDocument() {
        login("userName", "password", "admin", "admin");
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        String nameObject = "Test"+LocalDateTime.now().toString().replace(":","_");
        createDocument(nameObject);
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[16]/td[5]")).getText(),nameObject );
    }

    public void createDocument(String nameObject) {
        //Переход на страницу с делами

        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div/div[1]/div/div/div/div[2]/div[2]/div[1]/button")));
        wd.findElement(By.cssSelector("button.ant-btn.ant-btn-lg")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-footer']//button[.='Создать']")).click();
       // wd.findElement(By.xpath("//*[@id='root']/div[2]/div/div[2]/div/div[1]/div[3]/button[2]")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();
        wd.findElement(By.id("object_name")).sendKeys(nameObject);
        ///html/body/div[3]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]
        wd.findElement(By.xpath("//div[3]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
    }

}
