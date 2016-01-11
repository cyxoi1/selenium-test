package com.store.demoqa.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private final WebDriver driver;

    // locators
    private final By productCategoryMenuLocator = By.id("menu-item-33");
    private final By iphonesCategoryLocator = By.id("menu-item-37");
    private final By accountIconLocator = By.className("account_icon");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public IPhonesPage clickIphonesProductCategory() {
        Actions actions = new Actions(driver);
        WebElement elem = driver.findElement(productCategoryMenuLocator);
        actions.moveToElement(elem).perform();

        WebElement subElem = driver.findElement(iphonesCategoryLocator);
        actions.moveToElement(subElem);

        actions.click();
        actions.perform();

        return new IPhonesPage(driver);
    }

    public AccountPage clickAccountIcon() {
        driver.findElement(accountIconLocator).click();
        return new AccountPage(driver);
    }

}
