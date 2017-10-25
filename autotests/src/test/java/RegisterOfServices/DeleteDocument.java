package RegisterOfServices;

import Connection.ConnectionHB;
import HelpClasses.BusinessTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteDocument extends ConnectionHB {

    SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
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
