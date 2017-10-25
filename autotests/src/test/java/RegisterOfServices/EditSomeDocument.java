package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.WriteReadFromFile;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.File;
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

        //Получим данные из файла
        //Получить путь к тестовым файлам
        File testFile = new File("src/help-files/data-for-doc.txt");
        //Читаем из файла адрес сервера
        WriteReadFromFile readDataForCompare= new WriteReadFromFile(testFile.getAbsolutePath());
        //Заполнить массив тестовыми данными
        String[] dataFromFile = new String[16];
        for (int i=0; i<readDataForCompare.readFromFile().size();i++){
            dataFromFile[i]=readDataForCompare.readFromFile().get(i);
        }
        pasteData(nameObject, dataFromFile);
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        //Сравним измененный текст объекта с тем что есть в таблице
        assertEquals(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[5]")).getText(),nameObject);
    }

    public String pasteData(String nameObject, String[] dataFromFile) {
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
        wd.findElement(By.id("client_name")).sendKeys(dataFromFile[0]);

        wd.findElement(By.id("client_contact")).click();
        wd.findElement(By.id("client_contact")).clear();
        wd.findElement(By.id("client_contact")).sendKeys(dataFromFile[1]);

        wd.findElement(By.id("client_contact_2")).click();
        wd.findElement(By.id("client_contact_2")).clear();
        wd.findElement(By.id("client_contact_2")).sendKeys(dataFromFile[2]);

        wd.findElement(By.id("client_phone")).click();
        wd.findElement(By.id("client_phone")).clear();
        wd.findElement(By.id("client_phone")).sendKeys(dataFromFile[3]);

        wd.findElement(By.id("client_phone_2")).click();
        wd.findElement(By.id("client_phone_2")).clear();
        wd.findElement(By.id("client_phone_2")).sendKeys(dataFromFile[4]);

        wd.findElement(By.id("responsible_name")).click();
        wd.findElement(By.id("responsible_name")).clear();
        wd.findElement(By.id("responsible_name")).sendKeys(dataFromFile[5]);

        wd.findElement(By.id("client_email")).click();
        wd.findElement(By.id("client_email")).clear();
        wd.findElement(By.id("client_email")).sendKeys(dataFromFile[6]);

        wd.findElement(By.id("responsible_email")).click();
        wd.findElement(By.id("responsible_email")).clear();
        wd.findElement(By.id("responsible_email")).sendKeys(dataFromFile[7]);

        wd.findElement(By.id("work_type")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        wd.findElement(By.id("object_adress")).click();
        wd.findElement(By.id("object_adress")).clear();
        wd.findElement(By.id("object_adress")).sendKeys(dataFromFile[8]);

        wd.findElement(By.id("offer_price")).click();
        wd.findElement(By.id("offer_price")).clear();
        wd.findElement(By.id("offer_price")).sendKeys(dataFromFile[9]);

        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[5]/div[2]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[3]/div")).click();

        if (!wd.findElement(By.cssSelector("input.ant-checkbox-input")).isSelected()) {
            wd.findElement(By.cssSelector("input.ant-checkbox-input")).click();
        }

        wd.findElement(By.id("client_contract_price")).click();
        wd.findElement(By.id("client_contract_price")).clear();
        wd.findElement(By.id("client_contract_price")).sendKeys(dataFromFile[10]);

        wd.findElement(By.id("client_contract_number")).click();
        wd.findElement(By.id("client_contract_number")).clear();
        wd.findElement(By.id("client_contract_number")).sendKeys(dataFromFile[11]);

        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[7]/div[4]/div/div[2]/div/span/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[7]/div[4]/div/div[2]/div/span/div/input")).sendKeys(dataFromFile[12]);
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
        wd.findElement(By.id("contractor_contract_price")).sendKeys(dataFromFile[13]);

        wd.findElement(By.id("contractor_contract_number")).click();
        wd.findElement(By.id("contractor_contract_number")).clear();
        wd.findElement(By.id("contractor_contract_number")).sendKeys(dataFromFile[14]);

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
        wd.findElement(By.id("note")).sendKeys(dataFromFile[15]);
        //Сохранить
        wd.findElement(By.xpath("//html/body/div[2]/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button[2]")).click();
        return nameObject;
    }
}
