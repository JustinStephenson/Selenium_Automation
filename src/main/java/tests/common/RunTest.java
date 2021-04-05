package tests.common;

import driver.managers.DriverManager;
import driver.DriverModule;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import settings.*;

import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@Guice(modules = {DriverModule.class, PropertiesModule.class} )
public class RunTest {

    @Inject private DriverManager driverManager;
    @Inject public WebDriver driver;
    @Inject @Named("url") private String url;

    @BeforeTest
    public void setup() {
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterTest
    public void tearDown() {
        driverManager.quitDriver();
    }

}
