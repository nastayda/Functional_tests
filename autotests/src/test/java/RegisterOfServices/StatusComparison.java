package RegisterOfServices;

import Connection.ConnectionHB;
import HelpClasses.BaseClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

@Title("Проверка по статусам")
public class StatusComparison extends BaseClass {
    @Test
    @Title("Проверка по статусам")
    public void StatusComparison() throws Exception {
        login("userName", "password", "admin", "admin");
        //getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[1]/div/div[2]");
        //Проверка документов со статусом 77 Обращение,78 Коммерческое предложение, 79 Проект договора,
        // 80 Заключение договора и аванс, 81 Отказ, 82 1-й этап работ, 121 2-й этап работ, 122 Архив завершённых дел
        chekStatus77();
        checkStatus78();
        checkStatus79();
        checkStatus80();
        checkStatus82();
        checkStatus121();
        checkStatus122();
        checkStatus81();
    }

    @Step("7. Проверить статус Отказ")
    public void checkStatus81() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[8]/div/div[2]").size(),
                getCountStatus(81)
        );
    }

    @Step("7. Проверить статус Архив завершённых дел")
    public void checkStatus122() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[7]/div/div[2]").size(),
                getCountStatus(122)
        );
    }

    @Step("6. Проверить статус 2-й этап работ")
    public void checkStatus121() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[6]/div/div[2]").size(),
                getCountStatus(121)
        );
    }

    @Step("5. Проверить статус 1-й этап работ")
    public void checkStatus82() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[5]/div/div[2]").size(),
                getCountStatus(82)
        );
    }

    @Step("4. Проверить статус Заключение договора и аванс")
    public void checkStatus80() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[4]/div/div[2]").size(),
                    getCountStatus(80)
         );
    }

    @Step("3. Проверить статус Проект договора")
    public void checkStatus79() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[3]/div/div[2]").size(),
                    getCountStatus(79)
        );
    }

    @Step("2. Проверить статус Коммерческое предложение")
    public void checkStatus78() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[2]/div/div[2]").size(),
                getCountStatus(78)
        );
    }

    @Step("1. Проверить статус Обращение")
    public void chekStatus77() throws Exception {
        assertEquals(getNumbersFromTable("//div[@class='departments-tree']/div/ul/li[1]/div/div[2]").size(),
                     getCountStatus(77)
        );
    }

    @Step("Вернуть id всех элементов на странице")
    public ArrayList<Integer> getNumbersFromTable(String xpathStatus){
        wd.findElement(By.cssSelector("div.ant-col-22")).click();
        wd.findElement(By.xpath(xpathStatus)).click();
        int countRows = wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();;
        ArrayList<Integer> ids = new ArrayList<Integer>(countRows);
        for (int i = 1; i <= countRows; i++){
            ids.add(i-1,Integer.parseInt(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[2]")).getText()));
        }
        return ids;
    }

    @Step("Посчитать сколько записей в бд со статусом {0}")
    public int getCountStatus(int statusId) throws Exception {
        ConnectionHB conDB = new ConnectionHB();
        SessionFactory sessionFactory = conDB.setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from BusinessTable where status_id IN :status_id";
        List result = session.createQuery(hql).setParameter("status_id", statusId).list();

        session.getTransaction().commit();
        session.close();

        return result.size();
        }
}
