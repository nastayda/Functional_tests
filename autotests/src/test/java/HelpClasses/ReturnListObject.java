package HelpClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReturnListObject {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")
    public List<WebElement> table;

    public List<WebElement> getTable() {
        return table;
    }
}
