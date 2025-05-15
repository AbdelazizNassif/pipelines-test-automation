package com.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Products {

    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public Products(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    // locators
    By htmlBook = By.cssSelector(".product_cat-html");
    By htmlBookTitle = By.cssSelector(".product_cat-html h3");
    By addHtmlBookToCart = By.cssSelector(".product_cat-html .add_to_cart_button");
    By viewBasket = By.cssSelector(".product_cat-html [title='View Basket']");
    By htmlBookPrice = By.cssSelector(".product_cat-html ins .amount");
    // methods
    @Step("is html book displayed")
    public boolean isHtmlBookDisplayed() {
        page.scrollToElement(driver, htmlBook);
        return page.locateElement(driver, htmlBook).isDisplayed();
    }
    @Step("get html book title")
    public String getHtmlBookTitle () {
        return page.getTextOfElement(driver, htmlBookTitle);
    }
    @Step("add html book to cart")
    public void addHtmlBookToCart () {
        page.scrollVertically(driver, 50);
        page.clickElement(driver, addHtmlBookToCart);
    }
    @Step("Get book price")
    public Float getBookPrice () {
        String bookPrice = page.getTextOfElement(driver, htmlBookPrice);
        return Float.valueOf(bookPrice.substring(1, bookPrice.length()));
    }
    @Step("is view basket for html book displayed")
    public boolean isViewBasketOfHtmlBookDisplayed() {
        return page.locateElement(driver, viewBasket).isDisplayed();
    }
}
