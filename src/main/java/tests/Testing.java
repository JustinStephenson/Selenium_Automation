package tests;

import org.testng.annotations.Test;
import pageobjects.google.GoogleLandingPage;
import tests.common.RunTest;

import javax.inject.Inject;

public class Testing extends RunTest {

    @Inject private GoogleLandingPage googleLandingPage;

    @Test
    private void testing() {
        googleLandingPage.googleSearch("Cats");
    }
}

