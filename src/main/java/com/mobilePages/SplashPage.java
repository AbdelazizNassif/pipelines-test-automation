package com.mobilePages;

import filesReaders.PropertyFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SplashPage {

    AndroidDriver driver = null ;
    MobileBasePage mobilePage = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public SplashPage(AndroidDriver driver) {
        this.driver = driver;
        mobilePage = new MobileBasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("get temperature unit")
    public String getTemperatureUnit() {
        return mobilePage.locateElement(driver, By.id("tv_temperature_unit")).getText();
    }
    @Step("Swipe to rain and humidity icons")
    public void swipeToRainAndHumidityIcons (){
        mobilePage.swipeBottom(driver, 1500, 500);
    }
    @Step("short right swipe on times layout")
    public Set<Integer> shortRightSwipeOnTimesThenAddDisplayedTimes() {
        List<WebElement> hours = mobilePage.locateListOfElements(driver, By.id("tv_item_time"));
        Set<Integer> hoursSet = new HashSet<>();
        hours.forEach(h -> hoursSet.add(Integer.valueOf(h.getText().substring(0,2))) ) ;
        mobilePage.swipeRight(driver, hours.get(1), hours.get(0));
        return hoursSet;
    }
    @Step("long right swipe on times layout")
    public Set<Integer> longRightSwipeOnTimesThenAddDisplayedTimes() {
        List<WebElement> hours = mobilePage.locateListOfElements(driver, By.id("tv_item_time"));
        Set<Integer> hoursSet = new HashSet<>();
        hours.forEach(h -> hoursSet.add(Integer.valueOf(h.getText().substring(0,2))) ) ;
        mobilePage.swipeRight(driver, hours.get(2), hours.get(0));
        return hoursSet;
    }
    @Step("validate if five rain icons are displayed")
    public int isNextFiveRainDaysDisplayed () {
        return mobilePage.locateListOfElements(driver, By.id("rain_probability_cup") ).size();
    }
    @Step("validate if five rain icons are displayed")
    public int isWaterDropIconDisplayedForNextFiveDays () {
        return mobilePage.locateListOfElements(driver, By.id("chart_item_rain_probability") ).size();
    }

}
