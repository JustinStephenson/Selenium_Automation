package pageobjects.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.common.element.AbstractElement;

import java.lang.reflect.*;
import java.util.List;
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
        if (!WebElement.class.isAssignableFrom(field.getType()) &&
                !AbstractElement.class.isAssignableFrom(field.getType()) && !this.isDecoratableList(field)) {
            return null;
        }
        if (List.class.isAssignableFrom(field.getType())) {
            return proxyForListLocator(loader, locator);
        }
        return proxyForLocator(loader, locator, field);
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        } else {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return false;
            } else {
                Type listType = ((ParameterizedType)genericType).getActualTypeArguments()[0];
                if (!WebElement.class.equals(listType)) {
                    return false;
                } else {
                    return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null || field.getAnnotation(FindAll.class) != null;
                }
            }
        }
    }

    protected Object proxyForLocator(ClassLoader loader, ElementLocator locator, Field field) {
        InvocationHandler handler = new LocatingElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[] {WebElement.class, WrapsElement.class, Locatable.class}, handler);

        // Return the the sub class of Element that is being used
        Reflections reflections = new Reflections("pageobjects.common.element");
        Set<Class<? extends AbstractElement>> classes = reflections.getSubTypesOf(AbstractElement.class);
        for (Class<? extends AbstractElement> subClass : classes) {
            if (field.getType() == subClass) {
                try{
                    Constructor<? extends AbstractElement> constructor = subClass.getConstructor(WebElement.class);
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

    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List)Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }

}
