package RegisterOfServices;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.OutputStream;

public class OffInternetAndCheck {
    @Test
    public void offInternet(){
        try {
            // Execute command
            ///k C:\Users\danilkinaas\Desktop\offInternet.bat
            String command = "cmd /c start cmd.exe /k C:\\Users\\danilkinaas\\Desktop\\offInternet.bat \"\" DISABLED";
             Runtime.getRuntime().exec(command);

            // Get output stream to write from it
          /*  OutputStream out = child.getOutputStream();

            out.write("cd C:/ /r/n".getBytes());
            out.flush();
            out.write("dir /r/n".getBytes());
            out.close();*/
        } catch (IOException e) {
        }
    }
}
