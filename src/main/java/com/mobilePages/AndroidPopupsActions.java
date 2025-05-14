package com.mobilePages;

import filesReaders.PropertyFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidPopupsActions {

    AndroidDriver driver = null ;
    MobileBasePage mobilePage = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public AndroidPopupsActions(AndroidDriver driver) {
        this.driver = driver;
        mobilePage = new MobileBasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }

    @Step("allow popup - permission to *********")
    public void clickALLOWPopup () {
        mobilePage.clickElement(driver, By.xpath("//*[@text='ALLOW']"));
    }
    @Step("ALLOW popup - permission to *********")
    public void clickAllowPopup () {
        mobilePage.clickElement(driver, By.xpath("//*[@text='Allow']"));
    }
    @Step("close popup - close popup")
    public void closePopup () {
        mobilePage.clickElement(driver, By.id("bt_close"));
    }
    @Step("click while using the app")
    public void clickWhileUsingTheApp (){
        mobilePage.clickElement(driver, By.xpath("//*[@text='While using the app']"));
    }


}
