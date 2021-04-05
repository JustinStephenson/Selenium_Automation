package pageobjects.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.PageObject;

import javax.inject.Inject;

public class GooglePostSearchPage extends PageObject {

    @FindBy(css = "div[class='hstb-mitem hdtb-msel")
    private WebElement allButton;

    @FindBy(css = "a[data-hveid='CAEQAw']")
    private WebElement newsButton;

    @Inject
    public GooglePostSearchPage(WebDriver driver) {
        super(driver);
    }
}
