import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

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

    try {
      Process p=Runtime.getRuntime().exec("runas /user:Administrator cmd.exe /c start");

      BufferedReader BR=new BufferedReader(new InputStreamReader(p.getInputStream()));
      String l;
      while((l=BR.readLine()) != null){
        System.out.print(l);

      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
