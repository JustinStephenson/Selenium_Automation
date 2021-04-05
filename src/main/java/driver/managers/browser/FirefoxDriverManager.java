package driver.managers.browser;

import driver.managers.DriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }
}
