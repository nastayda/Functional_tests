package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.WriteReadFromFile;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;

import static org.testng.Assert.assertEquals;
//NOT USE
@Title("Сравнение отображаемых данных и введенных")
public class DataComparison extends BaseClass {
    @Test
    public void DataComparison(){
        login("userName", "password", "admin", "admin");
        assertEquals(getDataFromFile(),getDataFromPage());
    }
    @Step("Получить данные со страницы")
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

    @Step("Получить данные из файла")
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
        System.out.println(dataFromFile[1]);
        return dataFromFile;
    }

}
