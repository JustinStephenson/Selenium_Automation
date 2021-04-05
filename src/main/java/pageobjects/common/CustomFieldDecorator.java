package pageobjects.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.common.element.Element;

import java.lang.reflect.*;
import java.util.Set;

public class CustomFieldDecorator implements FieldDecorator {
    private final Logger logger = LoggerFactory.getLogger(CustomFieldDecorator.class);

    protected ElementLocatorFactory factory;

    public CustomFieldDecorator(ElementLocatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        return proxyForLocator(loader, locator, field);
    }

    protected Object proxyForLocator(ClassLoader loader, ElementLocator locator, Field field) {
        InvocationHandler handler = new LocatingElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[] {WebElement.class, WrapsElement.class, Locatable.class}, handler);

        // Return the the sub class of Element that is being used by @FindBy
        Reflections reflections = new Reflections("pageobjects.common.element");
        Set<Class<? extends Element>> classes = reflections.getSubTypesOf(Element.class);
        for (Class<? extends Element> subClass : classes) {
            if (field.getType() == subClass) {
                try{
                    Constructor<? extends Element> constructor = subClass.getConstructor(WebElement.class);
                    return constructor.newInstance(proxy);
                }
                catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                        InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return proxy;
    }

}
