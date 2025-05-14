package com.mobile.tests;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected AndroidDriver driver;
    private final static String APPIUM_CAPS_PROPERTY_FILE = "appiumCaps.properties";

    @BeforeAll
    @Description("init appium driver and caps")
    @DisplayName("init appium driver and caps")
    public void setupDriverAndCapabilities() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("deviceName", "Pixel XL");
        caps.setCapability("app", System.getProperty("user.dir") + "\\app\\weather-forecast.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }



    @AfterAll
    @Description("quit appium driver")
    @DisplayName("quit appium driver")
    public void quitDriver() {
        driver.quit();
    }
}
