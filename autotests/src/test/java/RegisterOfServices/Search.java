package RegisterOfServices;

import HelpClasses.BaseClass;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseClass {
   @Test
    public void Search(){
       login("userName", "password", "admin", "admin");
       //Positive test
       assertEquals(searchEverywhere("Customer1"),searchWithFilterEverywhere("Customer1"));
       //Negative test
      // assertEquals(searchEverywhere("Customer1231"),searchWithFilterEverywhere("customer1231"));
    }

    public int searchWithFilterEverywhere(String searchCondition) {
        wd.findElement(By.cssSelector("div.ant-select-selection__rendered")).click();
        wd.findElement(By.xpath("//div[2]/div/div/div/ul/li[1]")).click();
        wd.findElement(By.cssSelector("input.ant-input")).click();
        wd.findElement(By.cssSelector("input.ant-input")).clear();
        wd.findElement(By.cssSelector("input.ant-input")).sendKeys(searchCondition);
        int k=0;
        System.out.println(wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size());
        if (wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size()>0) {
            wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='1']")).click();
            for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
                for (int j = 1; j <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[1]/td")).size(); j++) {
                    //System.out.println(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr/td[" + i + "]")).getText());
                    if (wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText().equals(searchCondition)) {
                        k++;
                    }
                }
            }
        }
        return k;
    }
    public int searchEverywhere(String searchCondition) {
        wd.findElement(By.cssSelector("div.ant-select-selection__rendered")).click();
        int k=0;
        if (wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size()>0) {
            wd.findElement(By.xpath("//div[2]/div/div/div/ul/li[1]")).click();
            for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
                for (int j = 1; j <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[1]/td")).size(); j++) {
                    //System.out.println(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr/td[" + i + "]")).getText());
                    if (wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText().equals(searchCondition)) {
                        k++;
                    }
                }
            }
        }
        return k;
    }
}
