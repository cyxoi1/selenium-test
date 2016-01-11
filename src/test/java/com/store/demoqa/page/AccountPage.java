package com.store.demoqa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccountPage {

    private final WebDriver driver;

    // locators
    private final By userNameLocator = By.id("log");
    private final By passwordLocator = By.id("pwd");
    private final By loginLocator = By.id("login");
    private final By detailsLinkLocator = By.partialLinkText("Your Details");
    private final By addressLocator = By.id("wpsc_checkout_form_4");
    private final By saveProfileLocator = By.name("submit");
    private final By logoutLinkLocator = By.partialLinkText("Log out");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage addUsername(String username) {
        driver.findElement(userNameLocator).sendKeys(username);
        return this;
    }

    public AccountPage addPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public AccountPage clickLoginButton() {
        driver.findElement(loginLocator).click();
        // wait for login to complete
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(detailsLinkLocator));
        return this;
    }

    public AccountPage clickDetailsLink() {
        driver.findElement(detailsLinkLocator).click();
        return this;
    }

    public AccountPage clearAddress() {
        driver.findElement(addressLocator).clear();
        return this;
    }

    public AccountPage addAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
        return this;
    }

    public String getAddress() {
        return driver.findElement(addressLocator).getText();
    }

    public AccountPage clickSaveProfile() {
        driver.findElement(saveProfileLocator).submit();
        return this;
    }

    public LoginPage clickLogoutLink() {
        driver.findElement(logoutLinkLocator).click();
        return new LoginPage(driver);
    }

}
