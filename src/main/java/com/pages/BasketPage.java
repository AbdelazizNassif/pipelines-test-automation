package com.pages;

import filesReaders.PropertyFileReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BasketPage {

    WebDriver driver = null ;
    BasePage page = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("navigate to shop page")
    public void navigate () {
        String url = dotenv.get("APP_URL");
        url = "https://practice.automationtesting.in/basket/";
        page.navigateToURL(driver, url);
    }
    @Step("get product in the basket")
    public Map getProductInTheBasket () {
        By firstProductInCart = By.cssSelector(".cart_item");
        Map<String, Object> productDetailsMap = new HashMap<>();
        productDetailsMap.put("title", page.locateElement(driver, firstProductInCart).findElement(By.cssSelector(".product-name")).getText());
        productDetailsMap.put("price", page.locateElement(driver, firstProductInCart).findElement(By.cssSelector(".product-price")).getText());
        productDetailsMap.put("quantity", page.locateElement(driver, firstProductInCart).findElement(By.cssSelector(".product-quantity .quantity input")).getDomAttribute("value"));
        productDetailsMap.put("totalPrice", page.locateElement(driver, firstProductInCart).findElement(By.cssSelector(".product-subtotal")).getText());
        return productDetailsMap;
    }
    @Step("Click proceed to checkout")
    public CheckoutPage clickProceedToCheckout (){
        By proceedToCheckoutBtn = By.cssSelector(".checkout-button");
        page.scrollToElement(driver, proceedToCheckoutBtn);
        page.clickElement(driver, proceedToCheckoutBtn);
        return new CheckoutPage(driver);
    }
}
