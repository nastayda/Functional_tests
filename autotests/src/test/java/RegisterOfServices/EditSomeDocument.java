package RegisterOfServices;

import HelpClasses.BaseClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.time.LocalDateTime;
import org.openqa.selenium.*;

public class EditSomeDocument extends BaseClass {

    @Test
    public void EditDocument() {
        login("userName", "password", "admin", "admin");
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        //Генерим название объекта "Test"+дата
        String nameObject = "Test"+ LocalDateTime.now().toString().replace(":","_");
        String[] dataForCompare = {"12"};
        pasteData(nameObject);
        //Сравним измененный текст объекта с тем что есть в таблице
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[5]")).getText(),nameObject);
    }

    public String pasteData(String nameObject) {
        wd.findElement(By.cssSelector("div.departments-tree")).click();
        //Возьмем последнюю строку в таблице для редактирования
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]")).click();
        wd.findElement(By.id("change")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();


        //Заполняем в форме имя объекта
        wd.findElement(By.id("object_name")).sendKeys(nameObject);

        wd.findElement(By.id("client_name")).click();
        wd.findElement(By.id("client_name")).clear();
        wd.findElement(By.id("client_name")).sendKeys("Customer1");

        wd.findElement(By.id("client_contact")).click();
        wd.findElement(By.id("client_contact")).clear();
        wd.findElement(By.id("client_contact")).sendKeys("Testov1");

        wd.findElement(By.id("client_contact_2")).click();
        wd.findElement(By.id("client_contact_2")).clear();
        wd.findElement(By.id("client_contact_2")).sendKeys("Testov2");

        wd.findElement(By.id("client_phone")).click();
        wd.findElement(By.id("client_phone")).clear();
        wd.findElement(By.id("client_phone")).sendKeys("8 (888) 888-88-88");

        wd.findElement(By.id("client_phone_2")).click();
        wd.findElement(By.id("client_phone_2")).clear();
        wd.findElement(By.id("client_phone_2")).sendKeys("8 (000) 000-00-00");

        wd.findElement(By.id("client_email")).click();
        wd.findElement(By.id("client_email")).clear();
        wd.findElement(By.id("client_email")).sendKeys("test@t.t");

        wd.findElement(By.id("responsible_name")).click();
        wd.findElement(By.id("responsible_name")).clear();
        wd.findElement(By.id("responsible_name")).sendKeys("Testov3");

        wd.findElement(By.id("responsible_email")).click();
        wd.findElement(By.id("responsible_email")).clear();
        wd.findElement(By.id("responsible_email")).sendKeys("test1");

        wd.findElement(By.id("client_email")).click();
        wd.findElement(By.id("client_email")).clear();
        wd.findElement(By.id("client_email")).sendKeys("test1@t.t");

        wd.findElement(By.id("responsible_email")).click();
        wd.findElement(By.id("responsible_email")).clear();
        wd.findElement(By.id("responsible_email")).sendKeys("test2@t.t");

        wd.findElement(By.id("work_type")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        wd.findElement(By.id("object_adress")).click();
        wd.findElement(By.id("object_adress")).clear();
        wd.findElement(By.id("object_adress")).sendKeys("testAddress");

        wd.findElement(By.id("offer_price")).click();
        wd.findElement(By.id("offer_price")).clear();
        wd.findElement(By.id("offer_price")).sendKeys("9 999 999");

        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[5]/div[2]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[3]/div")).click();

        if (!wd.findElement(By.cssSelector("input.ant-checkbox-input")).isSelected()) {
            wd.findElement(By.cssSelector("input.ant-checkbox-input")).click();
        }

        wd.findElement(By.id("client_contract_price")).click();
        wd.findElement(By.id("client_contract_price")).clear();
        wd.findElement(By.id("client_contract_price")).sendKeys("99 999 999");

        wd.findElement(By.id("client_contract_number")).click();
        wd.findElement(By.id("client_contract_number")).clear();
        wd.findElement(By.id("client_contract_number")).sendKeys("1");

        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[7]/div[4]/div/div[2]/div/span/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[7]/div[4]/div/div[2]/div/span/div/input")).sendKeys("\\9");
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[7]/div[4]/div/div[2]/div/span/div/span")).click();

        wd.findElement(By.id("client_contract_price")).click();
        wd.findElement(By.id("client_contract_price")).clear();
        wd.findElement(By.id("client_contract_price")).sendKeys("11 111 111");

        wd.findElement(By.xpath("//div[2]/div/div[2]/div/div[1]/div[2]/form/div[8]/div[2]/div/div[1]/label/span")).click();
        wd.findElement(By.id("contractor_contract_price")).click();

        if (!wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[8]/div[1]/div/div/label/span/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[8]/div[1]/div/div/label/span/input")).click();
        }
        wd.findElement(By.id("contractor_contract_price")).click();
        wd.findElement(By.id("contractor_contract_price")).clear();
        wd.findElement(By.id("contractor_contract_price")).sendKeys("22 222 222");

        wd.findElement(By.id("contractor_contract_number")).click();
        wd.findElement(By.id("contractor_contract_number")).clear();
        wd.findElement(By.id("contractor_contract_number")).sendKeys("2");

        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[8]/div[4]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[5]/div")).click();
        wd.findElement(By.xpath("//div[2]/div/div[2]/div/div[1]/div[2]/form/div[9]/div[1]/div/div/div/div/div[3]/p")).click();

        wd.findElement(By.id("client_stage_1_price")).click();
        if (!wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).click();
        }
        if (wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).click();
        }
        if (!wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[1]/div/div/label/span/input")).click();
        }
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[3]/div")).click();

        ///html/body/div[2]/div/div[2]/div/div[1]/div[2]/form/div[9]/div[1]/div/div/div/div/div[4]/p
        wd.findElement(By.xpath("//div[2]/div/div[2]/div/div[1]/div[2]/form/div[9]/div[1]/div/div/div/div/div[4]/p")).click();
        if (!wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[3]/div[2]/div[1]/div/div/label/span/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[3]/div[2]/div[1]/div/div/label/span/input")).click();
        }
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[9]/div[2]/div[3]/div[2]/div[3]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[7]/div")).click();

        wd.findElement(By.id("note")).click();
        wd.findElement(By.id("note")).clear();
        wd.findElement(By.id("note")).sendKeys("OtherIfo");
        //Сохранить
        wd.findElement(By.xpath("//html/body/div[2]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
        return nameObject;
    }
}
