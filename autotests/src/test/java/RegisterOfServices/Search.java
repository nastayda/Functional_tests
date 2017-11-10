package RegisterOfServices;

import Connection.ConnectionHB;
import HelpClasses.BaseClass;
import HelpClasses.BusinessTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseClass {

    private SoftAssert softAssert = new SoftAssert( );

    @Title("Поиск по различным критериям")
    @Test
    public void Search( ) throws Exception {
        login( "userName", "password", "admin", "admin" );
        ArrayList<Integer> idList = getNumbersFromTable( );
        List rowsFromDB =  getRowsFromDB( idList );
        searchFromrResponsibleName( getSearchConditionCount( "ResponsibleName", rowsFromDB )[ 0 ] );
        searchFromClientName( getSearchConditionCount( "ClientName", rowsFromDB )[ 0 ] );
        searchFromObjectName( getSearchConditionCount( "ObjectName", rowsFromDB )[ 0 ] );
        searchFromContractorName( getSearchConditionCount( "ContractorName", rowsFromDB )[ 0 ] );
        searchFromWorkType( getSearchConditionCount( "WorkType", rowsFromDB )[ 0 ] );
        searchFromAddress( getSearchConditionCount( "ObjectAdress", rowsFromDB )[ 0 ] );
        searchFromContractDate( getSearchConditionCount( "ClientContractDate",rowsFromDB )[ 0 ] );
        searchFromContractNumber( getSearchConditionCount( "ClientContractNumber", rowsFromDB )[ 0 ] );
        searchFromNumberDocument( getSearchConditionCount( "Id", rowsFromDB )[ 0 ] );
        searchFromContractPrice( getSearchConditionCount( "ClientContractPrice", rowsFromDB )[ 0 ] );
        softAssert.assertAll( );
    }

    /*
    !!!Везде
    -№ дела id
    -Заказчик client_name
    -ФИО отвественного responsible_name
    -Объект object_name
    -Подрядчик contractor_name
    -Вид работ work_type Механическая безопасность
    -Адрес object_adress
    -Договор № client_contract_number
    -Дата договора client_contract_name
    -Цена договора client_contract_price
     */
    public void searchByEverywhere( ) throws Exception {
        String [] massCriteria ={
                "ResponsibleName","ClientName","ObjectName","ContractorName","WorkType", "ObjectAdress", "ClientContractDate","ClientContractNumber","ClientContractPrice"

        };
        softAssert.assertEquals( searchWithFilterFromBrowser( getSearchConditionCount( "ClientContractDate", getRowsFromDB( getNumbersFromTable( ) ) )[ 0 ], "//div[2]/div/div/div/ul/li[10]" ),
                Integer.parseInt( getSearchConditionCount( "ClientContractDate", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск везде " + getSearchConditionCount( "ClientContractDate", getRowsFromDB( getNumbersFromTable( ) ) )[ 0 ] + " провален"
        );
    }

    @Step("Поиск по дате контракта {0}")
    public void searchFromContractDate( String clientContractDate ) throws Exception {
        //Positive test
        //String[] resultSearch = getSearchConditionCount( "ContractorName", 16 );
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractDate, "//div[2]/div/div/div/ul/li[10]" ),
                Integer.parseInt( getSearchConditionCount( "ClientContractDate", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по дате " + clientContractDate + " провален"
        );
    }

    @Step("Поиск по номеру контракта {0}")
    public void searchFromContractNumber( String clientContractNumber ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractNumber, "//div[2]/div/div/div/ul/li[9]" ),
                Integer.parseInt( getSearchConditionCount( "ClientContractNumber", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по номеру контракта " + clientContractNumber + " провален"
        );
    }

    @Step("Поиск по цене контракта {0}")
    public void searchFromContractPrice( String clientContractPrice ) throws Exception {
        //Positive test
        //String[] resultSearch = getSearchConditionCount( "ContractorName", 16 );
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractPrice, "//div[2]/div/div/div/ul/li[11]" ),
                Integer.parseInt( getSearchConditionCount( "ClientContractPrice", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по цене контракта " + clientContractPrice + " провален"
        );
    }

    @Step("Поиск по адресу {0}")
    public void searchFromAddress( String objectAdress ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( objectAdress, "//div[2]/div/div/div/ul/li[8]" ),
                Integer.parseInt( getSearchConditionCount( "ObjectAdress", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по по адресу " + objectAdress + " провален"
        );
    }

    @Step("Поиск по имени подрядчика {0}")
    public void searchFromContractorName( String contractorName ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( contractorName, "//div[2]/div/div/div/ul/li[5]" ),
                Integer.parseInt( getSearchConditionCount( "ContractorName", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по имени подрядчика " + contractorName + " провален"
        );
    }

    @Step("Поиск по виду работ {0}")
    public void searchFromWorkType( String workType ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( workType, "//div[2]/div/div/div/ul/li[7]" ),
                Integer.parseInt( getSearchConditionCount( "WorkType", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по номеру контракта " + workType + " провален"
        );
    }

    @Step("Поиск по номеру дела {0}")
    public void searchFromNumberDocument( String clientContractNumber ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractNumber, "//div[2]/div/div/div/ul/li[2]" ),
                Integer.parseInt( getSearchConditionCount( "Id", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по номеру дела " + clientContractNumber + " провален"
        );
    }

    @Step("Поиск по имени заказчика {0}")
    public void searchFromClientName( String clientName ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientName, "//div[2]/div/div/div/ul/li[3]" ),
                Integer.parseInt( getSearchConditionCount( "ClientName", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по имени заказчика " + clientName + " провален"
        );
    }

    @Step("Поиск по ФИО ответсвенного {0}")
    public void searchFromrResponsibleName( String responsibleName ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( responsibleName, "//div[2]/div/div/div/ul/li[4]" ),
                Integer.parseInt( getSearchConditionCount( "ResponsibleName", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по ФИО ответсвенного " + responsibleName + " провален"
        );
    }

    @Step("Поиск по имени объекта {0}")
    public void searchFromObjectName( String objectName ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( objectName, "//div[2]/div/div/div/ul/li[6]" ),
                Integer.parseInt( getSearchConditionCount( "ObjectName", getRowsFromDB( getNumbersFromTable( ) ) )[ 1 ] ), "Поиск по имени объекта " + objectName + " провален"
        );
    }

    //@Step("4. Число элементво после  применения фильтра из браузера")
    public int searchWithFilterFromBrowser( String searchCondition, String filterXpath ) throws InterruptedException {
        wd.findElement(By.cssSelector("div.departments-tree")).click();
        wd.findElement( By.cssSelector( "div.ant-select-selection__rendered" ) ).click( );

        WebElement target = wd.findElement(By.xpath( filterXpath ));
        ((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView(true);", target);
       // Thread.sleep(500); //not sure why the sleep was needed, but it was needed or it wouldnt work :(
        target.click();

       // wd.findElement( By.xpath( filterXpath ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).clear( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).sendKeys( searchCondition );
        int k = wd.findElements( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr" ) ).size( );
        return k;
    }

    //@Step("Получить первое ненулевое условие поиска из браузера oldVersion")
    public String getSearchConditionOldVersion( int j ) {
        for (int i = 1; i <= wd.findElements( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr" ) ).size( ); i++) {
            String searchCondition = wd.findElement( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]" ) ).getText( );
            if (!searchCondition.equals( "" )) {
                return searchCondition;
            }
        }
        return "";
    }

    //@Step("3. Получить все номера дел из браузера")
    public ArrayList<Integer> getNumbersFromTable( ) {
        //Клик по левому меню "Обращения"
        wd.findElement( By.cssSelector( "div.departments-tree" ) ).click( );
        wd.findElement( By.cssSelector( "div.ant-select-selection__rendered" ) ).click( );
        wd.findElement( By.xpath( "//div[2]/div/div/div/ul/li[1]" ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).click( );
        int countRows = wd.findElements( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr" ) ).size( );
        ;
        ArrayList<Integer> ids = new ArrayList<Integer>( countRows );
        for (int i = 1; i <= countRows; i++) {
            ids.add( i - 1, Integer.parseInt( wd.findElement( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[" + i + "]/td[2]" ) ).getText( ) ) );
        }
        return ids;
    }

    //@Step("2. Получить все строки с № дел браузера из БД")
    public List<BusinessTable> getRowsFromDB( ArrayList<Integer> numbersFromTable ) throws Exception {
        ConnectionHB conDB = new ConnectionHB( );
        SessionFactory sessionFactory = conDB.setUp( );

        Session session = sessionFactory.openSession( );
        session.beginTransaction( );

        List<Integer> idList = numbersFromTable;

        String hql = "from BusinessTable where id IN :id";
        List result = session.createQuery( hql ).setParameter( "id", idList ).list( );
        for (BusinessTable document : (List<BusinessTable>) result) {
            // System.out.println(document.getId());
        }
        session.getTransaction( ).commit( );
        session.close( );
        return result;
    }

    //@Step("1. Получить первое ненулевое условие поиска из БД searchResult[0] + число повторений этого условия в наборе данных из бд searchResult[1]")
    public String[] getSearchConditionCount( String condition, List<BusinessTable> rowsFromDB ) throws Exception {
        //Получить все элементы из бд
        List result = rowsFromDB;
        int k = 0;
        //Инициализирвоать массив элементами для поиска
        String[] resultSearch = new String[]{ "", "" };

        for (BusinessTable item : (List<BusinessTable>) result) {
            Method method = item.getClass( ).getMethod( "get" + condition );
            if (method.invoke( item ) != null) {
                if (resultSearch[ 0 ].isEmpty( )) {
                    resultSearch[ 0 ] = method.invoke( item ).toString( );
                }
                if (!resultSearch[ 0 ].isEmpty( ) & method.invoke( item ).toString( ).contains( resultSearch[ 0 ] )) {
                    k++;
                    resultSearch[ 1 ] = Integer.toString( k );
                }
            }
        }
        return resultSearch;
    }

    public String[] getSearchConditionCountOldV2( String condition ) throws Exception {
        List result = getRowsFromDB( getNumbersFromTable( ) );
        int k = 1;
        String[] resultSearch = new String[]{ "", "" };
        EditSomeDocument getDataFromFileObject = new EditSomeDocument( );
        String[] dataFromFileMass = getDataFromFileObject.getDataFromFile( );
        //System.out.println(dataFromFileMass[0]);
        //В зависимости от того какое условие задано
        switch (condition) {
            case "id":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getId( ) != -10) {
                        //System.out.println(item.getId());
                        return new String[]{ Integer.toString( item.getId( ) ), "1" };
                    }
                }
                break;
            /*case "objectName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectName()!= null) {
                            resultSearch[0] = "Test2017";
                        if (item.getObjectName().contains(resultSearch[0])) {
                            k++;
                            resultSearch[1] = Integer.toString(k);
                        }
                    }
                }
                break;*/
            /*case "clientName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientName() != null) {
                        resultSearch[0] = dataFromFileMass[0];
                        if (resultSearch[0].contains(item.getClientName())) {
                            resultSearch[1] = Integer.toString(k++);
                        }
                    }
                }
                break;*/
            /*case "responsibleName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getResponsibleName() != null) {
                        resultSearch[0] = dataFromFileMass[5];
                        if (resultSearch[0].contains(item.getResponsibleName())) {
                            resultSearch[1] = Integer.toString(k++);
                        }
                    }
                }
                break;*/
            case "contractorName":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getContractorName( ) != "" & resultSearch[ 0 ] == "") {
                        resultSearch[ 0 ] = item.getContractorName( );
                        k++;
                    }
                    if (resultSearch[ 0 ].contains( item.getContractorName( ) )) {
                        resultSearch[ 1 ] = Integer.toString( k++ );
                    }
                }
                break;
            case "workType":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getWorkType( ) != "" & resultSearch[ 0 ] == "") {
                        resultSearch[ 0 ] = item.getWorkType( );
                        k++;
                    }
                    if (resultSearch[ 0 ].contains( item.getWorkType( ) )) {
                        resultSearch[ 1 ] = Integer.toString( k++ );
                    }
                }
                break;
            case "objectAdress":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getObjectAdress( ) != "" & resultSearch[ 0 ] == "") {
                        resultSearch[ 0 ] = item.getObjectAdress( );
                        k++;
                    }
                    if (resultSearch[ 0 ].contains( item.getObjectAdress( ) )) {
                        resultSearch[ 1 ] = Integer.toString( k++ );
                    }
                }
                break;
            /*case "clientContractNumber":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractNumber() != "" & resultSearch[0] == "") {
                        resultSearch[0] = item.getClientContractNumber();
                        k++;
                    }
                    if (resultSearch[0].contains(item.getClientContractNumber())) {
                        resultSearch[1] = Integer.toString(k++);
                    }
                }
                break;*/
            case "clientContractDate":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractDate( ) != null & resultSearch[ 0 ] == "") {
                        resultSearch[ 0 ] = item.getClientContractDate( ).toString( );
                    }
                    if (resultSearch[ 0 ].contains( item.getClientContractDate( ).toString( ) )) {
                        resultSearch[ 1 ] = Integer.toString( k++ );
                    }
                }
                break;
            case "clientContractPrice":
                for (BusinessTable item : (List<BusinessTable>) result) {
                    if (item.getClientContractPrice( ) != null & resultSearch[ 0 ] == "") {
                        resultSearch[ 0 ] = item.getClientContractPrice( ).toString( );
                    }
                    if (resultSearch[ 0 ].contains( item.getClientContractPrice( ).toString( ) )) {
                        resultSearch[ 1 ] = Integer.toString( k++ );
                    }
                }
                break;
        }

        return resultSearch;
    }

   /* public String[] getSearchConditionCountOldV3( String myCondition, int indexOfCondition ) throws Exception {
        //Получить все элементы из бд
        List result = getRowsFromDB( );
        int k = 0;
        //Из базового класса вытащили метод, который вернет текст из файла
        String[] dataFromFileMass = getDataFromFile( );
        //Инициализирвоать массив элементами для поиска
        String[] resultSearch = new String[]{ "", "" };
        if (myCondition.equals( "ObjectName" )) {
            resultSearch[ 0 ] = "Test2017";
        } else if (myCondition.equals( "WorkType" )) {
            resultSearch[ 0 ] = "Механическая безопасность";
        } else if (myCondition.equals( "ClientContractDate" )) {
            resultSearch[ 0 ] = getSearchConditionCountOldV1( "ClientContractDate" )[ 0 ];
        } else if (myCondition.equals( "ClientContractPrice" )) {
            resultSearch[ 0 ] = getSearchConditionCountOldV1( "ClientContractPrice" )[ 0 ];
        } else {
            resultSearch[ 0 ] = dataFromFileMass[ indexOfCondition ];
        }

        for (BusinessTable item : (List<BusinessTable>) result) {
            Method method = item.getClass( ).getMethod( "get" + myCondition );
            if (method.invoke( item ) != null) {
                if (method.invoke( item ).toString( ).contains( resultSearch[ 0 ] )) {
                    k++;
                    resultSearch[ 1 ] = Integer.toString( k );
                }
            }
        }
        //System.out.println(resultSearch[1]);
        return resultSearch;
    }*/
}
