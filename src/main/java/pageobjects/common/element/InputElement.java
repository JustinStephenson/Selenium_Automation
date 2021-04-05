package pageobjects.common.element;

import org.openqa.selenium.WebElement;

public abstract class InputElement extends Element {

    public InputElement(WebElement webElement) {
        super(webElement);
    }

    public void inputText(String value) {
        getWrappedElement().clear();
        getWrappedElement().sendKeys(value);
        logger.debug("updated element {} with value {}", getLocator(), value);
    }
}
