package RegisterOfServices;

import Connection.ConnectionHB;
import HelpClasses.BaseClass;
import HelpClasses.BusinessTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseClass {
   @Test
    public void Search() throws Exception {
       login("userName", "password", "admin", "admin");
       //Positive test
       //assertEquals(searchWithFilter(getSearchCondition(4), "//div[2]/div/div/div/ul/li[1]",true),
       //             searchWithFilter(getSearchCondition(4), "//div[2]/div/div/div/ul/li[1]", false));
       //getNumbersFromTable();
       //getRowsFromDB();
       getSearchCondition(0);
    }

    public int searchWithFilter(String searchCondition, String filterXpath, boolean searchUseFilter) {
        wd.findElement(By.cssSelector("div.ant-select-selection__rendered")).click();
        wd.findElement(By.xpath(filterXpath)).click();

        if (searchUseFilter){
        wd.findElement(By.cssSelector("input.ant-input")).click();
        wd.findElement(By.cssSelector("input.ant-input")).clear();
        wd.findElement(By.cssSelector("input.ant-input")).sendKeys(searchCondition);
        }
        int k=0;
       // System.out.println(wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size());
        if (wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size()>0) {
           // wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='1']")).click();
            for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
                for (int j = 1; j <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[1]/td")).size(); j++) {
                    //System.out.println(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr/td[" + i + "]")).getText());
                    if (wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText().equals(searchCondition)) {
                        k++;
                    }
                }
            }
        }
        return k;
    }

    //Получить первое ненулевое условие поиска
    public String getSearchConditionOldVersion(int j){
        for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
               String searchCondition = wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText();
               if (!searchCondition.equals("")){
                   return searchCondition;
               }
        }
        return "";
    }

    //Получить все номера дел из браузера
    public ArrayList<Integer> getNumbersFromTable(){
        wd.findElement(By.cssSelector("div.ant-select-selection__rendered")).click();
        wd.findElement(By.xpath("//div[2]/div/div/div/ul/li[1]")).click();
        wd.findElement(By.cssSelector("input.ant-input")).click();
        int countRows = wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();;
        ArrayList<Integer> ids = new ArrayList<Integer>(countRows);
        for (int i = 1; i <= countRows; i++){
                ids.add(i-1,Integer.parseInt(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[2]")).getText()));
        }
        return ids;
    }

    //Получить все строки с № дел браузера из БД
    public List getRowsFromDB() throws Exception {
        ConnectionHB conDB = new ConnectionHB();
        SessionFactory sessionFactory = conDB.setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Integer> idList = getNumbersFromTable();
        String hql = "from BusinessTable where id IN :id";
        List result = session.createQuery(hql).setParameter("id", idList).list();
       /* for ( BusinessTable document : (List<BusinessTable>) result ) {
            System.out.println(document);
        }*/
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //Получить первое ненулевое условие поиска
    public String getSearchCondition(int j) throws Exception {
        List result = getRowsFromDB();
        System.out.println(result.get(j));
        return "";
    }
}
