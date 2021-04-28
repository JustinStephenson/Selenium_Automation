package tests.common;

import driver.managers.DriverManager;
import driver.DriverModule;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import settings.*;

import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@Guice(modules = {DriverModule.class, PropertiesModule.class} )
public class RunTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Inject private DriverManager driverManager;
    @Inject public WebDriver driver;
    @Inject @Named("url") private String url;

    @BeforeTest
    public void setup() {
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        logger.debug("Closing browser session and quiting web driver");
        driverManager.quitDriver();
    }

}
