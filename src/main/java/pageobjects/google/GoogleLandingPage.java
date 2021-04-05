package pageobjects.google;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.PageObject;
import pageobjects.common.element.input.InputTextBox;

import javax.inject.Inject;

@Getter
public class GoogleLandingPage extends PageObject {

    @FindBy(css = "input[class='gLFyf gsfi']")
    private InputTextBox inputBox;

    @Inject
    public GoogleLandingPage(WebDriver driver) {
        super(driver);
    }

    public void googleSearch(String value) {
        inputBox.inputText(value);
        inputBox.getWrappedElement().sendKeys(Keys.ENTER);
    }
}
