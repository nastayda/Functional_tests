package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.WriteReadFromFile;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.io.File;

public class DataComparison extends BaseClass {
    @Test
    public void test(){
        //Получить путь к тестовым файлам
        File testFile = new File("src/help-files/data-for-doc.txt");
        //Читаем из файла адрес сервера
        WriteReadFromFile readDataForCompare= new WriteReadFromFile(testFile.getAbsolutePath());
        //Заполнить массив тестовыми данными
        String[] dataForCompare = new String[16];
        for (int i=0; i<readDataForCompare.readFromFile().size();i++){
            dataForCompare[i]=readDataForCompare.readFromFile().get(i);
        }
        login("userName", "password", "admin", "admin");
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");

        
    }

}
