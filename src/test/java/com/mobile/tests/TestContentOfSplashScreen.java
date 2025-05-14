package com.mobile.tests;

import com.mobilePages.AndroidPopupsActions;
import com.mobilePages.SplashPage;
import com.mobilePages.UnitsSettingsPage;
import io.qameta.allure.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import utils.WaitUtility;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestContentOfSplashScreen extends BaseTest{

    @BeforeAll
    @Description("preconditions - change temp unit anf time unit")
    @DisplayName("preconditions - change temp unit anf time unit")
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
    @Description("validate temp is displayed in F degrees")
    @DisplayName("validate temp is displayed in F degrees")
    public void a_validateTemperatureIsDisplayedInFehrinheit() {
        AndroidPopupsActions popupsActions = new AndroidPopupsActions(driver);
        popupsActions.clickALLOWPopup();
        popupsActions.clickAllowPopup();
        popupsActions.closePopup();
        SplashPage splashPage = new SplashPage(driver);
        Assertions.assertTrue(splashPage.getTemperatureUnit().toLowerCase().contains("f"),
                "temperature unit is not as expected");
    }
    @Test
    @Description("validate time is displayed in 24 h format")
    @DisplayName("validate time is displayed in 24 h format")
    public void b_validateTimeIsDisplayedInTwentyFourHrFormat() {
        SplashPage splashPage = new SplashPage(driver);
        Set<Integer> times = new HashSet<>();
        times = splashPage.shortRightSwipeOnTimesThenAddDisplayedTimes();
        for (int i = 0; i < 6; i++) {
            times.addAll(splashPage.longRightSwipeOnTimesThenAddDisplayedTimes());
            new WaitUtility().waitForInterval(1000);
        }
        MatcherAssert.assertThat("hours should contain numbers above 12",
                times, Matchers.hasSize(Matchers.greaterThan(12)));
        ;
        MatcherAssert.assertThat("hours should contain numbers above 12",
                times, Matchers.hasItem(Matchers.greaterThan(12)));
    }
    @Test
    @Description("validate next five days rain predictions are displayed")
    @DisplayName("validate next five days rain predictions are displayed")
    public void c_validateNextFiveDaysRainPredictionsAreDisplayed() {
        SplashPage splashPage = new SplashPage(driver);
        splashPage.swipeToRainAndHumidityIcons();
        Assertions.assertEquals(5, splashPage.isNextFiveRainDaysDisplayed(),
                "next five days are displayed");
        Assertions.assertEquals(5, splashPage.isWaterDropIconDisplayedForNextFiveDays(),
                "water icons is displayed for next five days are displayed");
    }
}
