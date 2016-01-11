package com.store.demoqa.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IPhonesPage {

    private final WebDriver driver;

    // locators
    private final By magicMouseFormLocator = By.name("product_40");
    private final By iphone4BlackFormLocator = By.name("product_96");
    private final By iphone4WhiteFormLocator = By.name("product_98");
    private final By iphone4PriceLocator = By.cssSelector("span.currentprice.pricedisplay.product_price_96");
    private final By checkoutButtonLocator = By.className("go_to_checkout");
    private final By continueShoppingButtonLocator = By.className("continue_shopping");

    public IPhonesPage(WebDriver driver) {
        this.driver = driver;
    }

    public IPhonesPage addMagicMouse() {
        driver.findElement(magicMouseFormLocator).submit();
        return this;
    }

    public IPhonesPage addIphone4Black() {
        driver.findElement(iphone4BlackFormLocator).submit();
        return this;
    }

    public IPhonesPage addIphone4White() {
        driver.findElement(iphone4WhiteFormLocator).submit();
        return this;
    }

    public String getIphone4Price() {
        return driver.findElement(iphone4PriceLocator).getText();
    }

    public float getIphone4PriceAsFloat() {
        String price = getIphone4Price();
        String s = price.split("\\$")[1];
        return Float.parseFloat(s);
    }

    public CheckoutPage clickCheckout() {
        WebElement e = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(checkoutButtonLocator));
        e.click();
        return new CheckoutPage(driver);
    }

    public IPhonesPage clickContinueShopping() {
        WebElement e = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(continueShoppingButtonLocator));
        e.click();
        return this;
    }
}
