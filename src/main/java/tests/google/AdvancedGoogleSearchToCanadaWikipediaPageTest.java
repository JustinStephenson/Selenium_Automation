package tests.google;

import org.testng.annotations.Test;
import pageobjects.google.GoogleLandingPage;
import pageobjects.google.GooglePostSearchPage;
import tests.common.RunTest;

import javax.inject.Inject;

public class AdvancedGoogleSearchToCanadaWikipediaPageTest extends RunTest {

    @Inject GoogleLandingPage googleLandingPage;
    @Inject GooglePostSearchPage googlePostSearchPage;

    @Test
    private void advancedGoogleSearchToCanadaWikipediaPageTest() {
        googleLandingPage.googleSearch("Canada Wikipedia");
        System.out.println(googleLandingPage.getInputBox().toString());
    }

}
