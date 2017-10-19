package Connection;

import HelpClasses.BusinessTable;
import RegisterOfServices.DeleteDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ConnectionHB {

    private SessionFactory sessionFactory;

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

    @Test
    public void testHbConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from BusinessTable").list();
        for ( BusinessTable document : (List<BusinessTable>) result ) {
            //System.out.println( "Event (" + document.getDate() + ") : " + event.getTitle() );
            System.out.println(document);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void updateData(){
      //  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       // String hqlUpdate = "update BusinessTable set objectName = :newName where objectName ='Test2017-10-19T12:24:20.112'";
        //session.createQuery(hqlUpdate).setString("newName", "Test".concat((LocalDateTime.now()).toString())).executeUpdate();
        String hql = "delete from BusinessTable where objectName = :name";
        session.createQuery(hql).setString("name", "Test2017-10-19T12:24:20.112").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
