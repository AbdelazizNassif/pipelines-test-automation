package com.mobile.tests;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected AndroidDriver driver;
    private final static String APPIUM_CAPS_PROPERTY_FILE = "appiumCaps.properties";

    @BeforeClass
    @Description("init appium driver and caps")
    public void setupDriverAndCapabilities() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("deviceName", "Pixel XL");
        caps.setCapability("app", System.getProperty("user.dir") + "\\app\\weather-forecast.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }


    @AfterClass
    @Description("quit appium driver")
    public void quitDriver() {
        driver.quit();
    }
}
