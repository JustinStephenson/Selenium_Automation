package settings;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.*;

public class PropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), getProperties());
    }

    private Map<String, String> getProperties() {
        return AutomationSettings.getProperties();
    }
}
