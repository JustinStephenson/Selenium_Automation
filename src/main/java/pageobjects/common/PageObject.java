package pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class PageObject {

    public PageObject(WebDriver driver) {
        PageFactory.initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(driver)), this);
    }

}
