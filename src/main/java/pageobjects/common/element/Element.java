package pageobjects.common.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Element implements WrapsElement {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    /**
     * Get the WebElement wrapped
     * @return WebElement
     */
    public WebElement getWrappedElement() {
        return webElement;
    }

    /**
     * Get the locator used to find the DOM WebElement
     * @return String the represents the locator value of the wrapped WebElement
     */
    public String getLocator() {
        return webElement.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }

}
