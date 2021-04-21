package pageobjects.common.element;

import org.openqa.selenium.WebElement;

public abstract class AbstractInputElement extends AbstractElement {

    public AbstractInputElement(WebElement webElement) {
        super(webElement);
    }

    /**
     * Send input to WebElement
     */
    public void inputText(String value) {
        getWrappedElement().clear();
        getWrappedElement().sendKeys(value);
        logger.debug("updated element {} with value {}", getLocator(), value);
    }
}
