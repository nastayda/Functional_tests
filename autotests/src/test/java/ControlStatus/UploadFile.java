package ControlStatus;

import HelpClasses.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UploadFile extends BaseClass {
    @Test
    public void uploadFile(){
        login();
        wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='Test2017-11-09T12_53_30.600']")).click();
                                  //*[@id="root"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[1]/div[2]/div/span[2]
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[1]/div[2]/div/span[2]")).click();
//*[@id="root"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[1]/div[2]/div/span[2]
       // wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//html/body/div[2]/div/div[2]/div/div[1]
        //Ждем пока загрузится кнопка Добавить
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until( ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[2]/div/div[2]/div/div[1]")));

        wd.findElement(By.xpath("//div[@class='FileExplorer']/div/div[1]/div/ul/li/ul/li[1]/span[2]/span[2]")).click();
        wd.findElement(By.xpath("//div[@class='dragField']/i")).click();
        wd.findElement(By.cssSelector("input.dragField__input")).click();
        wd.findElement( By.xpath("//div[@class='ant-modal-footer']//button[.='Закрыть']")).click();
    }
}
