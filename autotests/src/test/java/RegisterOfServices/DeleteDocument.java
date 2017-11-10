package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.BusinessTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeleteDocument extends BaseClass {
    @Test
    @Title("Удалить строку")
    public void DeleteDocument() throws Exception {
        login();
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        System.out.println("id=" +wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[2]")).getText());
        int countBefore = getCountBefore();
        //Установить соединение к БД и удалить последний элемент в таблице
        setUpConnection();
        deleteRow(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[2]")).getText());
        //Обновить страницу. Посчитать число строк в таблице после удаления
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        int countAfter = getCountBefore();
        assertEquals(countBefore-1, countAfter);
    }

    @Step("Получить число элементов со страницы")
    public int getCountBefore() {
        //Посчитать число строк в таблице до удаления
        return wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();
    }

    SessionFactory sessionFactory;
    @Step("Установить соединение с БД")
    public void setUpConnection() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    //Вывод всей информации из таблицы
    public void getRows(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //String hql = "from BusinessTable where id=:id";
        //List result = session.createQuery(hql).setParameter("id", 541).list();
        List result = session.createQuery("from BusinessTable ").list();
        for ( BusinessTable document : (List<BusinessTable>) result ) {
            System.out.println(document);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Step("Удаление созданной строки")
    public void deleteRow(String nameObject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "delete from BusinessTable where id = :name";
        session.createQuery(hql).setString("name", nameObject).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
