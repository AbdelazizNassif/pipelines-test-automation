package com.ecomm.tests;

import driverSettigns.DriverFactory;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    public WebDriver driver = null;
    public Dotenv dotenv = null;

    @BeforeAll
    @Description("Initialize chrome browser")
    @DisplayName("Initialize chrome browser")
    public void initializeDriver() {
        driver = new DriverFactory().getDriver();
        driver.manage().window().maximize();
        dotenv = Dotenv.load();
    }

    @AfterAll
    @Description("Initialize chrome browser")
    @DisplayName("Initialize chrome browser")
    public void quitDriver() {
        driver.quit();
    }

    @AfterEach
    @Description("Take screenshot")
    @DisplayName("Take screenshot")
    public void takeScreenShot(TestInfo testInfo) {
        new ScreenshotUtil().
                attachScreenshotToAllureReport(driver, testInfo.getDisplayName());
    }


}


