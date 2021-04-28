package pageobjects.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pageobjects.common.AbstractPageObject;
import pageobjects.common.element.click.Button;

import javax.inject.Inject;
import java.util.List;

@Getter
public class GooglePostSearchPage extends AbstractPageObject {

    // Task-bar
    @FindBy(css = "div[class='hstb-mitem hdtb-msel")
    private Button allButton;
    @FindBy(css = "a[data-hveid='CAEQAw']")
    private Button newsButton;
    @FindBy(css = "a[data-hveid='CAEQBA']")
    private Button maps;
    @FindBy(css = "a[data-hveid='CAEQBQ']")
    private Button images;
    @FindBy(css = "a[data-hveid='CAEQBg']")
    private Button videos;
    @FindBy(css = "a[href='/preferences']")
    private Button settings;

    @FindAll({@FindBy(css = "h3[class='LC20lb DKV0Md']")})
    private List<WebElement> googleSearchOptions;

    @FindAll({@FindBy(css = "h3[class='LC20lb DKV0Md']")})
    private List<Button> googleSearchOptionsTest;

    @Inject
    public GooglePostSearchPage(WebDriver driver) {
        super(driver);
    }

    //TODO: update when Abstract Element lists is working
    public void clickFirstLink() {
        googleSearchOptions.get(0).click();
    }

    public void clickFromSettings(SettingsOptions settingsOptions) {
        settings.click();
        Button option = new Button(driver.findElement(By.cssSelector(settingsOptions.cssLocator)));
        option.click();
    }

  @Getter
  @AllArgsConstructor
  public enum SettingsOptions {
    SEARCH_SETTINGS("Search settings", ""),
    LANGUAGES("Languages", ""),
    HIDE_EXPLICIT_RESULTS(
        "Hide explicit results", ""),
    ADVANCED_SEARCH("Advanced search", "a[href^='/advanced_search']"),
    SEARCH_HISTORY("Search History", ""),
    YOUR_DATA_IN_SEARCH("You data in Search", ""),
    SEARCH_HELP("Search help", "");

    private final String name;
    private final String cssLocator;
    }
}
