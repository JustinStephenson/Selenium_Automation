package driver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import driver.managers.browser.ChromeDriverManager;
import driver.managers.DriverManager;
import driver.managers.browser.FirefoxDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import settings.AutomationSettings;

public class DriverModule extends AbstractModule {

    @SneakyThrows
    @Override
    protected void configure() {
        AutomationSettings.config();
        switch (DriverType.valueOf(System.getProperty("browser"))) {
            case CHROME -> bind(DriverManager.class).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
            case FIREFOX -> bind(DriverManager.class).to(FirefoxDriverManager.class).in(Scopes.SINGLETON);
            default -> throw new Exception("A valid browser was not chosen");
        }
    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }
}
