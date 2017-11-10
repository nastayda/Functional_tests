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
        login( );
        ArrayList<Integer> idList = getNumbersFromTable( );
        List rowsFromDB = getRowsFromDB( idList );
        searchFromrResponsibleName( getSearchConditionCount( "ResponsibleName", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ResponsibleName", rowsFromDB )[ 1 ] );
        searchFromClientName( getSearchConditionCount( "ClientName", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ClientName", rowsFromDB )[ 1 ] );
        searchFromObjectName( getSearchConditionCount( "ObjectName", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ObjectName", rowsFromDB )[ 1 ] );
        searchFromContractorName( getSearchConditionCount( "ContractorName", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ContractorName", rowsFromDB )[ 1 ] );
        searchFromWorkType( getSearchConditionCount( "WorkType", rowsFromDB )[ 0 ],
                getSearchConditionCount( "WorkType", rowsFromDB )[ 1 ] );
        searchFromAddress( getSearchConditionCount( "ObjectAdress", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ObjectAdress", rowsFromDB )[ 1 ] );
        searchByContractDate( getSearchConditionCount( "ClientContractDate", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ClientContractDate", rowsFromDB )[ 1 ] );
        searchFromContractNumber( getSearchConditionCount( "ClientContractNumber", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ClientContractNumber", rowsFromDB )[ 1 ] );
        searchFromNumberDocument( getSearchConditionCount( "Id", rowsFromDB )[ 0 ],
                getSearchConditionCount( "Id", rowsFromDB )[ 1 ] );
        searchFromContractPrice( getSearchConditionCount( "ClientContractPrice", rowsFromDB )[ 0 ],
                getSearchConditionCount( "ClientContractPrice", rowsFromDB )[ 1 ] );
        searchByEverywhere(rowsFromDB);
        softAssert.assertAll( );
    }

    @Step("Поиск везде(по всем 10 критериям)")
    public void searchByEverywhere( List rowsFromDB ) throws Exception {
        String[] massCriteria = {
                "ResponsibleName", "ClientName", "ObjectName", "ContractorName", "WorkType",
                "ObjectAdress", "ClientContractDate", "ClientContractNumber", "ClientContractPrice"
        };
        for (String item : massCriteria) {
            softAssert.assertEquals( searchWithFilterFromBrowser( getSearchConditionCount( item, rowsFromDB )[ 0 ], "//div[2]/div/div/div/ul/li[1]" ),
                    Integer.parseInt( getSearchConditionCount( item, rowsFromDB )[ 1 ] ),
                   "Поиск везде по критерию " + item + " " + getSearchConditionCount( item, rowsFromDB )[ 0 ] + " провален"
            );
        }
    }

    @Step("Поиск по дате контракта {0}")
    public void searchByContractDate( String clientContractDate, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractDate, "//div[2]/div/div/div/ul/li[10]" ),
                Integer.parseInt( countFromDB ), "Поиск по дате контракта " + clientContractDate + " провален"
        );
    }

    @Step("Поиск по номеру договора {0}")
    public void searchFromContractNumber( String clientContractNumber, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractNumber, "//div[2]/div/div/div/ul/li[9]" ),
                Integer.parseInt( countFromDB ), "Поиск по номеру договора " + clientContractNumber + " провален"
        );
    }

    @Step("Поиск по цене договора {0}")
    public void searchFromContractPrice( String clientContractPrice, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractPrice, "//div[2]/div/div/div/ul/li[11]" ),
                Integer.parseInt( countFromDB ), "Поиск по цене договора " + clientContractPrice + " провален"
        );
    }

    @Step("Поиск по адресу {0}")
    public void searchFromAddress( String objectAdress, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( objectAdress, "//div[2]/div/div/div/ul/li[8]" ),
                Integer.parseInt( countFromDB ), "Поиск по по адресу " + objectAdress + " провален"
        );
    }

    @Step("Поиск по имени подрядчика {0}")
    public void searchFromContractorName( String contractorName, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( contractorName, "//div[2]/div/div/div/ul/li[5]" ),
                Integer.parseInt( countFromDB ), "Поиск по имени подрядчика " + contractorName + " провален"
        );
    }

    @Step("Поиск по виду работ {0}")
    public void searchFromWorkType( String workType, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( workType, "//div[2]/div/div/div/ul/li[7]" ),
                Integer.parseInt( countFromDB ), "Поиск по номеру контракта " + workType + " провален"
        );
    }

    @Step("Поиск по номеру дела {0}")
    public void searchFromNumberDocument( String clientContractNumber, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientContractNumber, "//div[2]/div/div/div/ul/li[2]" ),
                Integer.parseInt( countFromDB ), "Поиск по номеру дела " + clientContractNumber + " провален"
        );
    }

    @Step("Поиск по имени заказчика {0}")
    public void searchFromClientName( String clientName, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( clientName, "//div[2]/div/div/div/ul/li[3]" ),
                Integer.parseInt( countFromDB ), "Поиск по имени заказчика " + clientName + " провален"
        );
    }

    @Step("Поиск по ФИО ответсвенного {0}")
    public void searchFromrResponsibleName( String responsibleName, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( responsibleName, "//div[2]/div/div/div/ul/li[4]" ),
                Integer.parseInt( countFromDB ), "Поиск по ФИО ответсвенного " + responsibleName + " провален"
        );
    }

    @Step("Поиск по имени объекта {0}")
    public void searchFromObjectName( String objectName, String countFromDB ) throws Exception {
        //Positive test
        softAssert.assertEquals( searchWithFilterFromBrowser( objectName, "//div[2]/div/div/div/ul/li[6]" ),
                Integer.parseInt( countFromDB ), "Поиск по имени объекта " + objectName + " провален"
        );
    }

    //@Step("4. Число элементво после  применения фильтра из браузера")
    public int searchWithFilterFromBrowser( String searchCondition, String filterXpath ) throws InterruptedException {
        wd.findElement( By.cssSelector( "div.departments-tree" ) ).click( );
        wd.findElement( By.cssSelector( "div.ant-select-selection__rendered" ) ).click( );

        WebElement target = wd.findElement( By.xpath( filterXpath ) );
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].scrollIntoView(true);", target );
        // Thread.sleep(500); //not sure why the sleep was needed, but it was needed or it wouldnt work :(
        target.click( );

        // wd.findElement( By.xpath( filterXpath ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).click( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).clear( );
        wd.findElement( By.cssSelector( "input.ant-input" ) ).sendKeys( searchCondition );
        int k = wd.findElements( By.xpath( "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr" ) ).size( );
        return k;
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

}
