package HelpClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReturnListObject {
    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> table;

    public List<WebElement> getTable() {
        return table;
    }


}
