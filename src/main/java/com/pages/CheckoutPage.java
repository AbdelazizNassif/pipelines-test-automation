package com.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    // locators
    By orderDetails = By.cssSelector("#order_review_heading");
    By orderReview = By.cssSelector("#order_review");
    By productName = By.cssSelector(".cart_item .product-name");
    By productTotal = By.cssSelector(".product-total .amount");
    By orderSubtotal = By.cssSelector(".cart-subtotal .amount");
    By orderTotalPrice = By.cssSelector(".order-total .amount");
    @Step("get product title")
    public String getProductTitle () {
        page.scrollToElement(driver, productName);
        return page.getTextOfElement(driver, productName);
    }
    @Step("get product total")
    public String getProductTotal() {
        return page.getTextOfElement(driver, productTotal);
    }
    @Step("get order subtotal")
    public String getOrderSubtotal () {
        return page.getTextOfElement(driver, orderSubtotal);
    }
    @Step("get order total with taxes")
    public Float getOrderTotal () {
        String orderTotal = page.getTextOfElement(driver, orderTotalPrice);
        return Float.valueOf(orderTotal.substring(1, orderTotal.length()));
    }


}
