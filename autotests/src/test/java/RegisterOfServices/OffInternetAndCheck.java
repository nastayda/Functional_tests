package RegisterOfServices;

import HelpClasses.BaseClass;
import HelpClasses.BusinessTable;
import HelpClasses.ReturnListObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class OffInternetAndCheck extends BaseClass{
    @Test
    public void offInternet() throws NoSuchMethodException {
      /* try {
            // Execute command
            ///k C:\Users\danilkinaas\Desktop\offInternet.bat
            String command = "cmd /c start cmd.exe /k C:\\Users\\danilkinaas\\Desktop\\offInternet.bat \"\" DISABLED";
             Runtime.getRuntime().exec(command);*/

            // Get output stream to write from it
          /*  OutputStream out = child.getOutputStream();

            out.write("cd C:/ /r/n".getBytes());
            out.flush();
            out.write("dir /r/n".getBytes());
            out.close();*/
        //} catch (IOException e) {
        //}

    }
   // private WebDriver wd;
    private ReturnListObject message;

    @BeforeMethod
    private void init() {
        //wd = new FirefoxDriver();
        login("userName", "password", "admin", "admin");
        message = new ReturnListObject();
        PageFactory.initElements(wd, message);
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    //@Test
    public void testsKendoUI() throws InterruptedException {
       // doubleClick(wd, message.kendo);
        if (message.getTable().size() > 0) {
            for (WebElement sm : message.getTable()) {
                    System.out.println(sm.getText());
            }
        }
    }
}