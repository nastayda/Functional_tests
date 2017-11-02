package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.WriteReadFromFile;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.File;
import java.time.LocalDateTime;
import org.openqa.selenium.*;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Редактирование данных и сравнение с отображаемыми данными")
public class EditSomeDocument extends BaseClass {

    @Title("Редактирование данных и сравнение с отображаемыми данными")
    @Test
    public void EditDocument() {
        login("userName", "password", "admin", "admin");
        pasteData();
        assertEquals(getDataFromPage(),getDataFromFile());
    }

    @Step("2. Получить данные со страницы")
    public String[] getDataFromPage() {
        String[] dataFromPage = new String[16];
        //Возьмем последнюю строку в таблице для редактирования
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]")).click();

        if (wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[1]/div[2]/div/span[1]")).getText().equals(
                wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[2]")).getText())){
            dataFromPage[0]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div")).getText();
            dataFromPage[1]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[2]/div[2]/div")).getText();
            dataFromPage[2]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[3]/div[2]/div")).getText();
            dataFromPage[3]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[4]/div[2]/div")).getText().substring(2);
            dataFromPage[4]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[5]/div[2]/div")).getText().substring(2);
            dataFromPage[5]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[3]/div[2]/div/pre")).getText();
            dataFromPage[6]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[3]/div/div[6]/div[2]/div")).getText();
            dataFromPage[7]="test2@t.t";
            dataFromPage[8]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/div/div[6]/div[2]/div/pre")).getText();
            dataFromPage[9]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[5]/div[1]/p/span")).getText();
            dataFromPage[10]="99 999 999";
            dataFromPage[11]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[7]/div[3]")).getText().substring(3);
            dataFromPage[12]="\\\\9";
            dataFromPage[13]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[8]/div[2]/p/span")).getText();
            dataFromPage[14]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[8]/div[3]")).getText().substring(3);
            dataFromPage[15]=wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div/div/div/div[12]/pre")).getText();
        }
        return dataFromPage;
    }

    @Step("3. Получить данные из файла")
    public String[] getDataFromFile() {
        //Получить путь к тестовым файлам
        File testFile = new File("src/help-files/data-for-doc.txt");
        //Читаем из файла адрес сервера
        WriteReadFromFile readDataForCompare= new WriteReadFromFile(testFile.getAbsolutePath());
        //Заполнить массив тестовыми данными
        String[] dataFromFile = new String[16];
        for (int i=0; i<readDataForCompare.readFromFile().size();i++){
            dataFromFile[i]=readDataForCompare.readFromFile().get(i);
        }
        //System.out.println(dataFromFile[1]);
        return dataFromFile;
    }

    @Step("1. Вставка данных в форму")
    public String pasteData() {
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
        goToAllDocs();
        fillObjectName(nameObject);
        fillClientName(dataFromFile[0]);
        fillClientContractor(dataFromFile[1]);
        fillClientContractor2(dataFromFile[2]);
        fillClientPhone(dataFromFile[3]);
        fillClientPhone2(dataFromFile[4]);
        fillRespName(dataFromFile[5]);
        fillClientEmail(dataFromFile[6]);
        fillRespEmail(dataFromFile[7]);
        choseWorkType();
        fillObjectAddress(dataFromFile[8]);
        fillOfferPrice(dataFromFile[9]);
        fillDate();
        checkSomeBox();
        fillClientContractorPrice(dataFromFile[10]);
        fillClientContractorNumber(dataFromFile[11]);
        fillPrepaymentAndStages(dataFromFile);
        return nameObject;
    }

    @Step("18. Заполнить вкладки Аванс, 1-й этап, 2-й этап")
    public void fillPrepaymentAndStages(String[] dataFromFile) {
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
    }

    @Step("17. Заполнить номер основного договора {0}")
    public void fillClientContractorNumber(String s) {
        wd.findElement(By.id("client_contract_number")).click();
        wd.findElement(By.id("client_contract_number")).clear();
        wd.findElement(By.id("client_contract_number")).sendKeys(s);
    }

    @Step("16. Заполнить коммерческое предложение на сумму {0}")
    public void fillClientContractorPrice(String s) {
        wd.findElement(By.id("client_contract_price")).click();
        wd.findElement(By.id("client_contract_price")).clear();
        wd.findElement(By.id("client_contract_price")).sendKeys(s);
    }

    @Step("15. Выбрать договоры основной и подрядный")
    public void checkSomeBox() {
        if (!wd.findElement(By.cssSelector("input.ant-checkbox-input")).isSelected()) {
            wd.findElement(By.cssSelector("input.ant-checkbox-input")).click();
        }
    }

    @Step("14. Заполниь дату коммерческого предложения")
    public void fillDate() {
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[5]/div[2]/div/div[2]/div/span/div/span")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-calendar-tbody']/tr[5]/td[3]/div")).click();
    }

    @Step("13. Заполниь коммерческое предложение {0}")
    public void fillOfferPrice(String s) {
        wd.findElement(By.id("offer_price")).click();
        wd.findElement(By.id("offer_price")).clear();
        wd.findElement(By.id("offer_price")).sendKeys(s);
    }

    @Step("12. Заполнить адрес {0}")
    public void fillObjectAddress(String s) {
        wd.findElement(By.id("object_adress")).click();
        wd.findElement(By.id("object_adress")).clear();
        wd.findElement(By.id("object_adress")).sendKeys(s);
    }

    @Step("11. Выбрать вид работы - Механическая безопасность")
    public void choseWorkType() {
        wd.findElement(By.id("work_type")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();
    }

    @Step("10. Заполнить мейл ответсвенного {0}")
    public void fillRespEmail(String s) {
        wd.findElement(By.id("responsible_email")).click();
        wd.findElement(By.id("responsible_email")).clear();
        wd.findElement(By.id("responsible_email")).sendKeys(s);
    }

    @Step("9. Заполнить мейл заказчика {0}")
    public void fillClientEmail(String s) {
        wd.findElement(By.id("client_email")).click();
        wd.findElement(By.id("client_email")).clear();
        wd.findElement(By.id("client_email")).sendKeys(s);
    }

    @Step("8. Заполнить имя ответсвенного")
    public void fillRespName(String s) {
        wd.findElement(By.id("responsible_name")).click();
        wd.findElement(By.id("responsible_name")).clear();
        wd.findElement(By.id("responsible_name")).sendKeys(s);
    }

    @Step("7. Заполнить телефон заказчика2 {0}")
    public void fillClientPhone2(String s) {
        wd.findElement(By.id("client_phone_2")).click();
        wd.findElement(By.id("client_phone_2")).clear();
        wd.findElement(By.id("client_phone_2")).sendKeys(s);
    }

    @Step("6. Заполнить телефон заказчика {0}")
    public void fillClientPhone(String s) {
        wd.findElement(By.id("client_phone")).click();
        wd.findElement(By.id("client_phone")).clear();
        wd.findElement(By.id("client_phone")).sendKeys(s);
    }

    @Step("5. Заполнить имя контакта2 {0}")
    public void fillClientContractor2(String s) {
        wd.findElement(By.id("client_contact_2")).click();
        wd.findElement(By.id("client_contact_2")).clear();
        wd.findElement(By.id("client_contact_2")).sendKeys(s);
    }

    @Step("4. Заполнить имя контакта {0}")
    public void fillClientContractor(String s) {
        wd.findElement(By.id("client_contact")).click();
        wd.findElement(By.id("client_contact")).clear();
        wd.findElement(By.id("client_contact")).sendKeys(s);
    }

    @Step("3. Заполнить имя заказчика {0}")
    public void fillClientName(String s) {
        wd.findElement(By.id("client_name")).click();
        wd.findElement(By.id("client_name")).clear();
        wd.findElement(By.id("client_name")).sendKeys(s);
    }

    @Step("2. Заполнить имя объекта {0}")
    public void fillObjectName(String nameObject) {
        //Возьмем последнюю строку в таблице для редактирования
        wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]")).click();
        wd.findElement(By.id("change")).click();
        wd.findElement(By.id("object_name")).click();
        wd.findElement(By.id("object_name")).clear();
        //Заполняем в форме имя объекта
        wd.findElement(By.id("object_name")).sendKeys(nameObject);
    }

    @Step("1. Переход на страницу Обращения")
    public void goToAllDocs() {
        wd.findElement(By.cssSelector("div.departments-tree")).click();
    }
}
