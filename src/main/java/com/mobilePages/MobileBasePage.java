package com.mobilePages;


import filesReaders.PropertyFileReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtility;

import java.time.Duration;
import java.util.*;

public class MobileBasePage {

    private WebDriverWait wait;
    final private PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public MobileBasePage(WebDriver driver) {
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }

    synchronized protected WebElement locateElement(AndroidDriver driver, By elementLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        return driver.findElement(elementLocator);
    }

    synchronized protected List<WebElement> locateListOfElements(AndroidDriver driver, By elementsLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementsLocator));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(elementsLocator, 0));
        return driver.findElements(elementsLocator);
    }

    synchronized protected void clickElement(AndroidDriver driver, By elementLocator) {
        locateElement(driver, elementLocator).click();
    }

    synchronized protected void typeOnInputField(AndroidDriver driver, By elementLocator, String text) {
        locateElement(driver, elementLocator).sendKeys(text);
    }

    synchronized protected void slowTypeOnInputField(AndroidDriver driver, By elementLocator, String text) {
        for (int letterIndex = 0; letterIndex < text.length(); letterIndex++) {
            locateElement(driver, elementLocator).sendKeys(String.valueOf(text.charAt(letterIndex)));
        }
        new WaitUtility().waitForInterval(25);
    }

    synchronized protected String getTextOfElement(AndroidDriver driver, By elementLocator) {
        String elementText = locateElement(driver, elementLocator).getText();
        return elementText;
    }

    synchronized protected String getTextOfElement(WebElement element) {
        return element.getText();
    }

    synchronized protected String getAttributeOfElement(AndroidDriver driver, By elementLocator, String attributeKey) {
        return locateElement(driver, elementLocator).getDomAttribute(attributeKey);
    }

    synchronized protected List<String> getTextOfListOfElements(AndroidDriver driver, By elementLocator) {
        List<WebElement> elements = locateListOfElements(driver, elementLocator);
        List<String> elementTextList = new ArrayList<>();

        for (WebElement element : elements) {
            elementTextList.add(element.getText());
        }
        return elementTextList;
    }

    synchronized protected void swipeRight (AndroidDriver driver, WebElement rightElement, WebElement leftElement) {
        int centerY = rightElement.getLocation().getY() + rightElement.getSize().getHeight() / 2;
        int startX = rightElement.getLocation().getX() + (int) (rightElement.getSize().getWidth());
        int endX = leftElement.getLocation().getX() + (int) (leftElement.getSize().getWidth());
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    synchronized protected void swipeBottom (AndroidDriver driver, int bottomLocation, int upperLocation) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 500, bottomLocation));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), 500, upperLocation));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
}
