package pageobjects.common.element;

import org.openqa.selenium.WebElement;

public abstract class AbstractClickElement extends AbstractElement {

    public AbstractClickElement(WebElement webElement) {
        super(webElement);
    }

    /**
     * Click on WebElement
     */
    public void click() {
        final String locatorString = getLocator();
        logger.debug("Attempting to click on {}", locatorString);
        getWrappedElement().click();
        logger.debug("Clicked on element {}", locatorString);
    }
}
