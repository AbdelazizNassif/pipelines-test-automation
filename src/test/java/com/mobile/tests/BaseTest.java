package com.mobile.tests;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BaseTest {
    protected AndroidDriver driver;
    private final static String APPIUM_CAPS_PROPERTY_FILE = "appiumCaps.properties";

    @BeforeClass
    @Description("init appium driver and caps")
    public void setupDriverAndCapabilities() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.userName", "abdelaziznassif_ieLJbE");
        caps.setCapability("browserstack.accessKey", "Jd1YdAybYvZbg94pEWsL");
        caps.setCapability("app", "bs://c5cb5a1ce77b660d8e3d00bd079e281217ccc33d");
        caps.setCapability("deviceName", "OnePlus 9");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        driver = new AndroidDriver(
                new URL("http://abdelaziznassif_ieljbe.browserstack.com"), caps);
    }



    @AfterClass
    @Description("quit appium driver")
    public void quitDriver() {
        driver.quit();
    }
}
