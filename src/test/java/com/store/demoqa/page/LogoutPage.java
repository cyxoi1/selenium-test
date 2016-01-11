package com.store.demoqa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LogoutPage {

    private final WebDriver driver;

    private final By backLink = By.partialLinkText("Back");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickBackToHomeLink() {
        driver.findElement(backLink).click();
        return new HomePage(driver);
    }

}
