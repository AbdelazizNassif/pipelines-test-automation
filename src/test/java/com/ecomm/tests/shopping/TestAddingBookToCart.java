package com.ecomm.tests.shopping;

import com.ecomm.tests.BaseTest;
import com.pages.*;
import filesReaders.JsonFileReader;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.util.HashMap;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAddingBookToCart extends BaseTest {

    Float htmlBookPrice;
    Integer quantity;
    String bookName;


    @BeforeAll
    @Description("preconditions - navigate to home page")
    @DisplayName("preconditions - navigate to home page")
    public void preconditions_navigateToHomePage() {
        new HomePage(driver).navigate();
        // read test data
        JsonFileReader testData = new JsonFileReader("dataByKey/bookOrderDetails.json");
        quantity = testData.getJsonIntegerValueByKey("quantity");
        bookName = testData.getJsonStringValueByKey("bookName");
    }

    @Test
    @Description("1- validate existence of html book in the products")
    @DisplayName("1- validate existence of html book in the products")
    public void a_validateExistenceOfHtmlBook () {
        Products products = new Products(driver);
        Assertions.assertEquals(true, products.isHtmlBookDisplayed(),
                "html book is not displayed");
        Assertions.assertEquals(bookName, products.getHtmlBookTitle(),
                "html book title is not as expected");
        // get book price
        htmlBookPrice = new Products(driver).getBookPrice();
    }

    @Test
    @Description("2- validate adding html book to the basket")
    @DisplayName("2- validate adding html book to the basket")
    public void b_validateAddingHtmlBookToTheBasket () {
        Products products = new Products(driver);
        products.addHtmlBookToCart();
        Assertions.assertEquals(true, products.isViewBasketOfHtmlBookDisplayed(),
                "view basket for html book is not displayed");
    }

    @Test
    @Description("3- Validate Details Of Book In The Shopping Cart")
    @DisplayName("3- Validate Details Of Book In The Shopping Cart")
    public void c_validateDetailsOfBookInTheShoppingCart () {
        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickShoppingCart();
        HashMap productDetails = (HashMap) new BasketPage(driver).getProductInTheBasket();
        System.out.println(productDetails);
        Assertions.assertEquals(String.valueOf(quantity),  productDetails.get("quantity"), "quantity is not as expected");
        Assertions.assertEquals(bookName, productDetails.get("title"), "book name is not as expected");
        Assertions.assertTrue(productDetails.get("price").toString().contains(String.valueOf(htmlBookPrice)), "price is not as expected");
        Assertions.assertTrue(productDetails.get("totalPrice").toString().contains(String.valueOf(htmlBookPrice)), "total price is not as expected");
    }

    @Test
    @Description("4- validate billing details")
    @DisplayName("4- validate billing details")
    public void d_validateBillingDetails () {
        Float taxesPercent = 1.02F;
        // get order total and subtotal from shopping page
        CheckoutPage checkoutPage = new BasketPage(driver).clickProceedToCheckout();
        Assertions.assertTrue(checkoutPage.getProductTitle().contains(bookName),
                "Book name is not as expected in billing details");
        Assertions.assertTrue(checkoutPage.getProductTotal().contains(String.valueOf(htmlBookPrice)) ,
                "product total price is not as expected");
        Assertions.assertTrue(checkoutPage.getOrderSubtotal().contains(String.valueOf(htmlBookPrice)),
                "");
        // get order total and subtotal from billing page
        Assertions.assertEquals(String.valueOf(htmlBookPrice * taxesPercent) , String.valueOf(checkoutPage.getOrderTotal()),
                "order total price with 2% taxes is not as expected") ;
    }
}
