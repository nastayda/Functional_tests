import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;

class test {
  public static void main (String[] args) {
    //WriteReadFromFile readData= new WriteReadFromFile("C:\\test\\notes3.txt");
   // System.out.println();
   /*try {
      // Execute command
     //netsh interface set interface name="Подключение по локальной сети" admin=DISABLED
      String command = "cmd /c start cmd.exe /K netsh interface set interface name=\"Подключение по локальной сети\" admin=DISABLED";
      Runtime.getRuntime().exec(command);
    } catch (IOException e) {
    }*/

    /*try {
      //C:\Users\danilkinaas\Desktop\offInternet.bat
      Process p=Runtime.getRuntime().exec("cmd.exe /c start /k C:\\Users\\danilkinaas\\Desktop\\offInternet.bat");

      BufferedReader BR=new BufferedReader(new InputStreamReader(p.getInputStream()));
      String l;
      while((l=BR.readLine()) != null){
        System.out.print(l);

      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/

    /* //@Test
    public void testsKendoUI( ) throws InterruptedException {
        // private WebDriver wd;
        ReturnListObject message;
        message = new ReturnListObject( );
        PageFactory.initElements( wd, message );
        wd.manage( ).timeouts( ).implicitlyWait( 0, TimeUnit.SECONDS );
        if (message.getTable( ).size( ) > 0) {
            for (WebElement sm : message.getTable( )) {
                System.out.println( sm.getText( ) );
            }
        }
    }*/
  }
}
