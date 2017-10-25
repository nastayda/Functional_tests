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
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeleteDocument extends BaseClass {
    @Test
    public void DeleteDocument() throws Exception {
        login("userName", "password", "admin", "admin");
        //Переход на страницу с делами
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        //Посчитать число строк в таблице до удаления
        int countBefore = wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();
        //Установить соединение к БД и удалить последний элемент в таблице
        setUpConnection();
        deleteRow(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[last()]/td[5]")).getText());
        //Обновить страницу. Посчитать число строк в таблице после удаления
        wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        int countAfter = wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();
        assertEquals(countBefore-1, countAfter);
    }

    SessionFactory sessionFactory;
    //@BeforeClass
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
        List result = session.createQuery("from BusinessTable ").list();
        for ( BusinessTable document : (List<BusinessTable>) result ) {
            System.out.println(document);
        }
        session.getTransaction().commit();
        session.close();
    }
    //Удаление созданной строки
    public void deleteRow(String nameObject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "delete from BusinessTable where objectName = :name";
        session.createQuery(hql).setString("name", nameObject).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
