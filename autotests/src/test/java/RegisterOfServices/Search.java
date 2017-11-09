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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseClass {
    @Title("Поиск по различным критериям")
    @Test
    public void Search( ) throws Exception {
        login( "userName", "password", "admin", "admin" );
        //searchFromrResponsibleName();
        //searchFromClientName();
        //searchFromObjectName();
        //searchFromContractorName();
        searchFromWorkType();
        //dont work
        //searchFromNumberDocument();
    }
/*
Везде
-№ дела id
-Заказчик client_name
-ФИО отвественного responsible_name
-Объект object_name
-Подрядчик contractor_name
-Вид работ work_type Механическая безопасность
Адрес object_adress
Договор № client_contract_number
Дата договора client_contract_name
Цена договора client_contract_price
 */

    /* @Step("Поиск везде")
     public void searchFromEverywhere() throws Exception {
         //Positive test
         assertEquals( searchWithFilter( getSearchConditionCount("id")[0], "//div[2]/div/div/div/ul/li[1]"),
                 Integer.parseInt( getSearchConditionCount("id")[1])
         );
     }*/
    @Step("Поиск по имени подрядчика")
    public void searchFromContractorName( ) throws Exception {
        //Positive test
        String[] resultSearch = getSearchConditionCount( "ContractorName", 16 );
        assertEquals( searchWithFilter( resultSearch[ 0 ], "//div[2]/div/div/div/ul/li[5]" ),
                Integer.parseInt( resultSearch[ 1 ] )
        );
    }

    @Step("Поиск по виду работ")
    public void searchFromWorkType( ) throws Exception {
        //Positive test
        String[] resultSearch = getSearchConditionCount( "WorkType", 0 );
        assertEquals( searchWithFilter( resultSearch[ 0 ], "//div[2]/div/div/div/ul/li[7]" ),
                Integer.parseInt( resultSearch[ 1 ] )
        );
    }

    @Step("Поиск по номеру дела")
    public void searchFromNumberDocument( ) throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount( "ClientContractNumber", 11 )[ 0 ], "//div[2]/div/div/div/ul/li[2]" ),
                Integer.parseInt( getSearchConditionCount( "ClientContractNumber", 11 )[ 1 ] )
        );
    }

    @Step("Поиск по имени заказчика")
    public void searchFromClientName( ) throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount( "ClientName", 0 )[ 0 ], "//div[2]/div/div/div/ul/li[3]" ),
                Integer.parseInt( getSearchConditionCount( "ClientName", 0 )[ 1 ] )
        );
    }

    @Step("Поиск по ФИО ответсвенного")
    public void searchFromrResponsibleName( ) throws Exception {
        //Positive test
        assertEquals( searchWithFilter( getSearchConditionCount( "ResponsibleName", 5 )[ 0 ], "//div[2]/div/div/div/ul/li[4]" ),
                Integer.parseInt( getSearchConditionCount( "ResponsibleName", 5 )[ 1 ] )
        );
    }

    @Step("Поиск по имени объекта")
    public void searchFromObjectName( ) throws Exception {
        //Positive test
        String[] resultSearch = getSearchConditionCount( "ObjectName", 0 );
        assertEquals( searchWithFilter( resultSearch[ 0 ], "//div[2]/div/div/div/ul/li[6]" ),
                Integer.parseInt( resultSearch[ 1 ] )
        );
    }

    //@Step("4. Число элементво после  применения фильтра")
    public int searchWithFilter( String searchCondition, String filterXpath ) {
        wd.findElement( By.cssSelector( "div.ant-select-selection__rendered" ) ).click( );
        wd.findElement( By.xpath( filterXpath ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).clear( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).sendKeys( searchCondition );
        int k = wd.findElements( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr" ) ).size( );
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
    public List<BusinessTable> getRowsFromDB( ) throws Exception {
        ConnectionHB conDB = new ConnectionHB( );
        SessionFactory sessionFactory = conDB.setUp( );

        Session session = sessionFactory.openSession( );
        session.beginTransaction( );

        List<Integer> idList = getNumbersFromTable( );

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
    public String[] getSearchConditionCountOldV1( String condition ) throws Exception {
        /*List result = getRowsFromDB();
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
        }*/
        //Получить все элементы из бд
        List result = getRowsFromDB( );
        int k = 0;
        //Из базового класса вытащили метод, который вернет текст из файла
        String[] dataFromFileMass = getDataFromFile( );
        //Инициализирвоать массив элементами для поиска
        String[] resultSearch = new String[]{ "", "" };
        if (condition.equals( "ObjectName" )) {
            resultSearch[ 0 ] = "Test2017";
            k++;
        } else {
            // resultSearch[0] = dataFromFileMass[ indexOfCondition ];
        }

        for (BusinessTable item : (List<BusinessTable>) result) {
            Method method = item.getClass( ).getMethod( "get" + condition );
            if (method.invoke( item ) != null) {
                if (method.invoke( item ).toString( ).contains( resultSearch[ 0 ] )) {
                    k++;
                    resultSearch[ 1 ] = Integer.toString( k );
                }
            }
        }
        //System.out.println(resultSearch[1]);
        //return resultSearch;
        //System.out.println(resultSearch[1]);
        return resultSearch;
    }

    public String[] getSearchConditionCountOldV2( String condition ) throws Exception {
        List result = getRowsFromDB( );
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

    public String[] getSearchConditionCount( String myCondition, int indexOfCondition ) throws Exception {
        //Получить все элементы из бд
        List result = getRowsFromDB( );
        int k = 0;
        //Из базового класса вытащили метод, который вернет текст из файла
        String[] dataFromFileMass = getDataFromFile( );
        //Инициализирвоать массив элементами для поиска
        String[] resultSearch = new String[]{ "", "" };
        if (myCondition.equals( "ObjectName" )) {
            resultSearch[ 0 ] = "Test2017";
            k++;
        }else if(myCondition.equals("WorkType")){
            resultSearch[ 0 ] = "Механическая безопасность";
            //k++;
        }
        else {
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
    }
}
