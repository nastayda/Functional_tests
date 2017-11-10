package HelpClasses;

import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;

public class OnOffNetAdapter {

    @Step("Сетевой адаптер {0}")
    public void onOffInternet( final String stateNetworkAdapter ) throws NoSuchMethodException {
        File testFile = new File( "src/help-files/offinternet.bat" );
        try {
            String command = "cmd /c start cmd.exe /k " + testFile.getAbsolutePath( ) + " " + stateNetworkAdapter;
            Runtime.getRuntime( ).exec( command );
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }
}
