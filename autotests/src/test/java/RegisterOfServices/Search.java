package RegisterOfServices;

import Connection.ConnectionHB;
import HelpClasses.BaseClass;
import HelpClasses.BusinessTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseClass {
    @Title("Поиск по различным критериям")
    @Test
    public void Search() throws Exception {
        login("userName", "password", "admin", "admin");
        searchFromrResponsibleName();
        searchFromClientName();
        searchFromObjectName();
    }

    @Step("Поиск везде")
    public void searchFromEverywhere() throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount("id")[0], "//div[2]/div/div/div/ul/li[1]"),
                Integer.parseInt( getSearchConditionCount("id")[1])
        );
    }

    @Step("Поиск по номеру дела")
    public void searchFromNumberDocument() throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount("clientContractNumber")[0], "//div[2]/div/div/div/ul/li[2]"),
                Integer.parseInt( getSearchConditionCount("clientContractNumber")[1])
        );
    }

    @Step("Поиск по имени заказчика")
    public void searchFromClientName() throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount("clientName")[0], "//div[2]/div/div/div/ul/li[3]"),
                      Integer.parseInt( getSearchConditionCount("clientName")[1])
                    );
    }

    @Step("Поиск по ФИО ответсвенного")
    public void searchFromrResponsibleName() throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount("responsibleName")[0], "//div[2]/div/div/div/ul/li[4]"),
                Integer.parseInt( getSearchConditionCount("responsibleName")[1])
        );
    }

    @Step("Поиск по имени объекта")
    public void searchFromObjectName() throws Exception {
        //Positive test
        //System.out.println("getSearchConditionCount "+Integer.parseInt( getSearchConditionCount("objectName")[1]));
        //System.out.println("searchWithFilter "+searchWithFilter( getSearchConditionCount("objectName")[0], "//div[2]/div/div/div/ul/li[6]"));
       String[] resultSearch = getSearchConditionCount("objectName");
        assertEquals( searchWithFilter( resultSearch[0], "//div[2]/div/div/div/ul/li[6]"),
                Integer.parseInt( resultSearch[1])
        );
    }

    //@Step("4. Число элементво после  применения фильтра")
    public int searchWithFilter(String searchCondition, String filterXpath) {
        wd.findElement(By.cssSelector("div.ant-select-selection__rendered")).click();
        wd.findElement(By.xpath(filterXpath)).click();
        wd.findElement(By.cssSelector("input.ant-input")).click();
        wd.findElement(By.cssSelector("input.ant-input")).clear();
        wd.findElement(By.cssSelector("input.ant-input")).sendKeys(searchCondition);
        int k=wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size();
        //System.out.println(k);
        /*if (wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size()>0) {
            for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
                for (int j = 1; j <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[1]/td")).size(); j++) {
                    //System.out.println(wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr/td[" + i + "]")).getText());
                    if (wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText().equals(searchCondition)) {
                        k++;
                    }
                }
            }
        }*/
        return k;
    }

    //@Step("Получить первое ненулевое условие поиска oldVersion")
    public String getSearchConditionOldVersion(int j){
        for (int i = 1; i <= wd.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")).size(); i++) {
               String searchCondition = wd.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText();
               if (!searchCondition.equals("")){
                   return searchCondition;
               }
        }
        return "";
    }

    //@Step("3. Получить все номера дел из браузера")
    public ArrayList<Integer> getNumbersFromTable(){
        //Клик по левому меню "Обращения"
        wd.findElement(By.cssSelector("div.departments-tree")).click();
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

    //@Step("2. Получить все строки с № дел браузера из БД")
    public List<BusinessTable> getRowsFromDB() throws Exception {
        ConnectionHB conDB = new ConnectionHB();
        SessionFactory sessionFactory = conDB.setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Integer> idList = getNumbersFromTable();

        String hql = "from BusinessTable where id IN :id";
        List result = session.createQuery(hql).setParameter("id", idList).list();
        for ( BusinessTable document : (List<BusinessTable>) result ) {
           // System.out.println(document.getId());
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //@Step("1. Получить первое ненулевое условие поиска из БД searchResult[0] + число повторений этого условия в наборе данных из бд searchResult[1]")
    public String[] getSearchConditionCountOld(String condition) throws Exception {
        List result = getRowsFromDB();
        int k=0;
        String [] resultSearch=new String[]{"",""};
        //В зависимости от того какое условие задано
        switch (condition) {
            case "id":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getId() != -10) {
                        System.out.println(item.getId());
                        return new String[]{Integer.toString(item.getId()), "1"};
                    }
                }
                break;
            case "objectName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectName()!= null) {
                        if (!item.getObjectName().equals("") & resultSearch[0] == "") {
                            resultSearch[0] = item.getObjectName();
                            k++;
                        }
                        if (resultSearch[0].contains(item.getObjectName())) {
                            resultSearch[1] = Integer.toString(k++);
                        }
                    }
                }
                break;
            case "clientName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientName() != "" & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientName();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getClientName())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "responsibleName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getResponsibleName() != ""& resultSearch[0] == "") {
                        resultSearch[0] = item.getResponsibleName();
                        k++;
                    }
                     if (resultSearch[0].contains(item.getResponsibleName())) {
                        resultSearch[1] = Integer.toString(k++);
                        // System.out.println(item.getResponsibleName());
                    }
                }

                break;
            case "contractorName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getContractorName() != ""& resultSearch[0] == "") {
                        resultSearch[0] = item.getContractorName();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getContractorName())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "workType":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getWorkType() != ""& resultSearch[0] == "") {
                        resultSearch[0] = item.getWorkType();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getWorkType())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "objectAdress":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectAdress() != ""& resultSearch[0] == "") {
                        resultSearch[0]  = item.getObjectAdress();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getObjectAdress())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractNumber":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractNumber() != "" & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractNumber();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getClientContractNumber())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractDate":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractDate() != null & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractDate().toString();
                    }
                    if (resultSearch[0].contains(item.getClientContractDate().toString())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractPrice":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractPrice() != null & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractPrice().toString();
                    }
                    if (resultSearch[0].contains(item.getClientContractPrice().toString())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
        }
        //System.out.println(resultSearch[1]);
        return resultSearch;
    }

    public String[] getSearchConditionCount(String condition) throws Exception {
        List result = getRowsFromDB();
        int k=1;
        String [] resultSearch=new String[]{"",""};
        EditSomeDocument getDataFromFileObject = new EditSomeDocument();
        String [] dataFromFileMass = getDataFromFileObject.getDataFromFile();
        //System.out.println(dataFromFileMass[0]);
        //В зависимости от того какое условие задано
        switch (condition) {
            case "id":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getId() != -10) {
                        //System.out.println(item.getId());
                        return new String[]{Integer.toString(item.getId()), "1"};
                    }
                }
                break;
            case "objectName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectName()!= null) {
                            resultSearch[0] = "Test2017";
                        if (item.getObjectName().contains(resultSearch[0])) {
                            k++;
                            resultSearch[1] = Integer.toString(k);
                        }
                    }
                }
                break;
            case "clientName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientName() != null) {
                        resultSearch[0] = dataFromFileMass[0];
                        if (resultSearch[0].contains(item.getClientName())) {
                            resultSearch[1] = Integer.toString(k++);
                        }
                    }
                }
                break;
            case "responsibleName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getResponsibleName() != null) {
                        resultSearch[0] = dataFromFileMass[5];
                        if (resultSearch[0].contains(item.getResponsibleName())) {
                            resultSearch[1] = Integer.toString(k++);
                        }
                    }
                }
                break;
            case "contractorName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getContractorName() != ""& resultSearch[0] == "") {
                        resultSearch[0] = item.getContractorName();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getContractorName())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "workType":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getWorkType() != ""& resultSearch[0] == "") {
                        resultSearch[0] = item.getWorkType();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getWorkType())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "objectAdress":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectAdress() != ""& resultSearch[0] == "") {
                        resultSearch[0]  = item.getObjectAdress();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getObjectAdress())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractNumber":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractNumber() != "" & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractNumber();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getClientContractNumber())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractDate":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractDate() != null & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractDate().toString();
                    }
                    if (resultSearch[0].contains(item.getClientContractDate().toString())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
            case "clientContractPrice":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractPrice() != null & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractPrice().toString();
                    }
                    if (resultSearch[0].contains(item.getClientContractPrice().toString())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;
        }
       // System.out.println(resultSearch[1]);
        return resultSearch;
    }
}
