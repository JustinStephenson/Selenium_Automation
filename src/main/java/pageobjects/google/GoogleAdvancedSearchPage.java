package pageobjects.google;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.AbstractPageObject;
import pageobjects.common.element.click.Button;
import pageobjects.common.element.input.InputTextBox;

import javax.inject.Inject;

@Getter
public class GoogleAdvancedSearchPage extends AbstractPageObject {

    // Section - Find pages with
    @FindBy(id = "xX4UFf")
    private InputTextBox allOfTheseWords;
    @FindBy(id = "CwYCWc")
    private InputTextBox theExactWordOrPhrase;
    @FindBy(id = "mSoczb")
    private InputTextBox anyOfTheseWords;
    @FindBy(id = "t2dX1c")
    private InputTextBox noneOfTheseWords;
    @FindBy(css = "input[name='as_nlo']")
    private InputTextBox numbersRangingFromStart;
    @FindBy(id = "input[name='as_nhi']")
    private InputTextBox numbersRangingFromEnd;

    @FindBy(css = "input[value='Advanced Search']")
    private Button advancedSearch;

    @Inject GoogleAdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    public void inputNumbersRangingFrom(String start, String end) {
        numbersRangingFromStart.inputText(start);
        numbersRangingFromEnd.inputText(end);
    }

}
