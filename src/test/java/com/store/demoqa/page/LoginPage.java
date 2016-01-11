package com.store.demoqa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    // locators
    private final By usernameLocator = By.id("user_login");
    private final By passwordLocator = By.id("user_pass");
    private final By loginButtonLocator = By.id("wp-submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return new LoginPage(driver);
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return new LoginPage(driver);
    }

    public ProfilePage clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
        return new ProfilePage(driver);
    }

}
