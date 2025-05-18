package com.ecomm.tests;

import driverSettigns.DriverFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.ScreenshotUtil;

public class BaseTest {

    public WebDriver driver = null;

    @Parameters("browser")
    @BeforeClass
    @Description("Initialize chrome browser")
    public void setup(String browserName) {
        switch (browserName.toUpperCase()) {
            case "CHROME":
                driver = new DriverFactory().getDriver("chrome");
                break;
            case "FIREFOX":
                driver = new DriverFactory().getDriver("firefox");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().window().maximize();
    }

    @AfterClass
    @Description("Initialize chrome browser")
    public void quitDriver() {
        driver.quit();
    }

    @AfterMethod
    @Description("Take screenshot")
    public void takeScreenShot(ITestResult testInfo) {
        new ScreenshotUtil().
                attachScreenshotToAllureReport(driver, testInfo.getName());
    }


}


