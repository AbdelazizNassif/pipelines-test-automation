package com.mobile.tests;

import com.mobilePages.AndroidPopupsActions;
import com.mobilePages.SplashPage;
import com.mobilePages.UnitsSettingsPage;
import io.qameta.allure.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WaitUtility;

import java.util.HashSet;
import java.util.Set;


public class TestContentOfSplashScreen extends BaseTest{

    @BeforeClass
    @Description("Precondition: set temp unit to fehrenheit and hour unit to 24 hr ")
    public void precondition_changeTempUnitAndHourUnit () {
        AndroidPopupsActions popupsActions = new AndroidPopupsActions(driver);
        UnitsSettingsPage unitsSettingsPage = new UnitsSettingsPage(driver);
        unitsSettingsPage.clickAllow();
        popupsActions.clickWhileUsingTheApp();
        unitsSettingsPage.selectFehrinheitUnit();
        unitsSettingsPage.selectTwentyFourHoursClock();
        unitsSettingsPage.confirmUnitsSelection();
    }

    @Test
    @Description("validate temp is displayed in fehrenheit")
    public void a_validateTemperatureIsDisplayedInFehrinheit() {
        AndroidPopupsActions popupsActions = new AndroidPopupsActions(driver);
        popupsActions.clickALLOWPopup();
        popupsActions.clickAllowPopup();
        popupsActions.closePopup();
        SplashPage splashPage = new SplashPage(driver);
        Assert.assertTrue(splashPage.getTemperatureUnit().toLowerCase().contains("f"),
                "temperature unit is not as expected");
    }
    @Test
    @Description("validate time is displayed in 24 h format")
    public void b_validateTimeIsDisplayedInTwentyFourHrFormat() {
        SplashPage splashPage = new SplashPage(driver);
        Set<Integer> times = new HashSet<>();
        times = splashPage.shortRightSwipeOnTimesThenAddDisplayedTimes();
        for (int i = 0; i < 6; i++) {
            times.addAll(splashPage.longRightSwipeOnTimesThenAddDisplayedTimes());
            new WaitUtility().waitForInterval(1000);
        }
        MatcherAssert.assertThat("day hours should be more than 12",
                times, Matchers.hasSize(Matchers.greaterThan(12)));
        ;
        MatcherAssert.assertThat("hours should contain numbers above 12",
                times, Matchers.hasItem(Matchers.greaterThan(12)));
    }
    @Test
    @Description("validate next five days rain predictions are displayed")
    public void c_validateNextFiveDaysRainPredictionsAreDisplayed() {
        SplashPage splashPage = new SplashPage(driver);
        splashPage.swipeToRainAndHumidityIcons();
        Assert.assertEquals(5, splashPage.isNextFiveRainDaysDisplayed(),
                "next five days are displayed");
        Assert.assertEquals(5, splashPage.isWaterDropIconDisplayedForNextFiveDays(),
                "water icons is displayed for next five days are displayed");
    }
}
