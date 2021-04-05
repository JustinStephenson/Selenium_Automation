package pageobjects.common.element;

import org.openqa.selenium.WebElement;

public abstract class ClickElement extends Element {

    public ClickElement(WebElement webElement) {
        super(webElement);
    }

    /**
     * Click on WebElement
     */
    public void click() {
        logger.debug("Attempting to click on {}", getLocator());
        getWrappedElement().click();
        logger.debug("Clicked on element {}", getLocator());
    }
}
