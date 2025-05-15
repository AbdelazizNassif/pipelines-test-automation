package com.mobilePages;

import filesReaders.PropertyFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UnitsSettingsPage {

    AndroidDriver driver = null ;
    MobileBasePage mobilePage = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public UnitsSettingsPage(AndroidDriver driver) {
        this.driver = driver;
        mobilePage = new MobileBasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }

    @Step("allow popup - permission to *********")
    public void clickAllow () {
        mobilePage.clickElement(driver, By.id("tv_allow"));
    }

    @Step("select fehrinheit unit")
    public void selectFehrinheitUnit (){
        mobilePage.locateListOfElements(driver,By.id("tv_toggle_right")).get(0).click();
    }
    @Step("select 24 h clock")
    public void selectTwentyFourHoursClock (){
        mobilePage.locateListOfElements(driver,By.id("tv_toggle_right")).get(1).click();
    }
    @Step("confirm units selection")
    public void confirmUnitsSelection () {
        mobilePage.clickElement(driver, By.id("tv_done"));
    }




}
