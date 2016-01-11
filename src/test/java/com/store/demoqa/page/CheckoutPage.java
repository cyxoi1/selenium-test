package com.store.demoqa.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage {

    private final WebDriver driver;

    // locators
    private final By continueButtonLocator = By.className("step2");
    private final By countryButtonLocator = By.id("uniform-current_country");
    private final By countrySelectLocator = By.id("current_country");
    private final By calculateButtonLocator = By.name("wpsc_submit_zipcode");
    private final By emailAddressFormLocator = By.id("wpsc_checkout_form_9");
    private final By billingFirstNameLocator = By.id("wpsc_checkout_form_2");
    private final By billingLastNameLocator = By.id("wpsc_checkout_form_3");
    private final By billingAddressLocator = By.id("wpsc_checkout_form_4");
    private final By billingCityLocator = By.id("wpsc_checkout_form_5");
    private final By billingStateLocator = By.id("wpsc_checkout_form_6");
    private final By billingPostalCodeLocator = By.id("wpsc_checkout_form_8");
    private final By billingPhoneLocator = By.id("wpsc_checkout_form_18");
    private final By billingCountrySelectLocator = By.id("wpsc_checkout_form_7");
    private final By shippingSameBillingCheckboxLocator = By.id("shippingSameBilling");
    private final By purchaseButtonLocator = By.className("input-button-buy");
    private final By cartItemsLocator = By.className("count");
    private final By cartTableLocator = By.className("checkout_cart");
    private final By removeButtonLocator = By.name("submit");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage clickContinue() {
        driver.findElement(continueButtonLocator).click();
        return this;
    }

    public CheckoutPage clickCountryMenu() {
        WebElement e = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(countryButtonLocator));
        e.click();
        return this;
    }

    public CheckoutPage selectCountry(String text) {
        Select dropdown = new Select(driver.findElement(countrySelectLocator));
        dropdown.selectByVisibleText(text);
        return this;
    }

    public CheckoutPage clickCalculateButton() {
        driver.findElement(calculateButtonLocator).submit();
        return this;
    }

    public CheckoutPage addEmailAddress(String emailAddress) {
        driver.findElement(emailAddressFormLocator).sendKeys(emailAddress);
        return this;
    }

    public CheckoutPage addBillingFirstName(String firstName) {
        driver.findElement(billingFirstNameLocator).sendKeys(firstName);
        return this;
    }

    public CheckoutPage addBillingLastName(String lastName) {
        driver.findElement(billingLastNameLocator).sendKeys(lastName);
        return this;
    }

    public CheckoutPage addBillingAddress(String address) {
        driver.findElement(billingAddressLocator).sendKeys(address);
        return this;
    }

    public CheckoutPage addBillingCity(String city) {
        driver.findElement(billingCityLocator).sendKeys(city);
        return this;
    }

    public CheckoutPage addBillingState(String state) {
        driver.findElement(billingStateLocator).sendKeys(state);
        return this;
    }

    public CheckoutPage selectBillingCountry(String text) {
        Select dropdown = new Select(driver.findElement(billingCountrySelectLocator));
        dropdown.selectByVisibleText(text);
        return this;
    }

    public CheckoutPage addBillingPostalCode(String postalCode) {
        driver.findElement(billingPostalCodeLocator).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage addBillingPhone(String phone) {
        driver.findElement(billingPhoneLocator).sendKeys(phone);
        return this;
    }

    public CheckoutPage clickShippingSameBillingCheckbox() {
        driver.findElement(shippingSameBillingCheckboxLocator).click();
        return this;
    }

    public TransactionResultsPage clickPurchaseButton() {
        driver.findElement(purchaseButtonLocator).submit();
        return new TransactionResultsPage(driver);
    }

    public String getItemCount() {
        return driver.findElement(cartItemsLocator).getText();
    }

    public int getItemCountAsInt() {
        return Integer.parseInt(getItemCount());
    }

    public CheckoutPage clickRemoveItem(int index) {
        WebElement table = driver.findElement(cartTableLocator);
        List<WebElement> rows = table.findElements(By.className("wpsc_product_remove"));
        rows.get(index).findElement(removeButtonLocator).submit();
        return this;
    }

}
