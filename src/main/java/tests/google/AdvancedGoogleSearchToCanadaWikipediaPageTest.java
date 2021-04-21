package tests.google;

import org.testng.annotations.Test;
import pageobjects.google.GoogleAdvancedSearchPage;
import pageobjects.google.GoogleLandingPage;
import pageobjects.google.GooglePostSearchPage;
import pageobjects.google.GooglePostSearchPage.SettingsOptions;
import tests.common.RunTest;

import javax.inject.Inject;

public class AdvancedGoogleSearchToCanadaWikipediaPageTest extends RunTest {

    @Inject private GoogleLandingPage googleLandingPage;
    @Inject private GooglePostSearchPage googlePostSearchPage;
    @Inject private GoogleAdvancedSearchPage googleAdvancedSearchPage;

    private static final String SEARCH_STRING = "Canada Wikipedia";

    @Test
    private void advancedGoogleSearchToCanadaWikipediaPageTest() {
        googleLandingPage.googleSearch(SEARCH_STRING);
        googlePostSearchPage.clickFromSettings(SettingsOptions.ADVANCED_SEARCH);
        googleAdvancedSearchPage.getAnyOfTheseWords().inputText(SEARCH_STRING);
        googleAdvancedSearchPage.getAllOfTheseWords().inputText(SEARCH_STRING);
        googleAdvancedSearchPage.getAdvancedSearch().click();
        googlePostSearchPage.clickFirstLink();
    }

}
