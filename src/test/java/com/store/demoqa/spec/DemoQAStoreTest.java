package com.store.demoqa.spec;

import com.store.demoqa.page.AccountPage;
import com.store.demoqa.page.CheckoutPage;
import com.store.demoqa.page.HomePage;
import com.store.demoqa.page.IPhonesPage;
import com.store.demoqa.page.LoginPage;
import com.store.demoqa.page.ProfilePage;
import com.store.demoqa.page.TransactionResultsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.Assert.*;


public class DemoQAStoreTest {

    private WebDriver driver;

    private static final String BASEURL = "http://store.demoqa.com/";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testIphone4OrderHasCorrectPrice() throws Exception {

        driver.get(BASEURL);

        // home page
        HomePage homePage = new HomePage(driver);

        // add black iphone4
        IPhonesPage iPhonesPage = homePage.clickIphonesProductCategory();
        iPhonesPage.addIphone4Black();
        Float iphone4Price = iPhonesPage.getIphone4PriceAsFloat();

        // proceed to checkout
        CheckoutPage checkoutPage = iPhonesPage.clickCheckout();
        checkoutPage.clickContinue();
        checkoutPage.clickCountryMenu();
        checkoutPage.selectCountry("USA");
        checkoutPage.clickCalculateButton();

        // add email, billing and shipping info
        checkoutPage.addEmailAddress("bob.smith@me.com");
        checkoutPage.addBillingFirstName("Robert");
        checkoutPage.addBillingLastName("Smith");
        checkoutPage.addBillingAddress("12345 Meadow Lane");
        checkoutPage.addBillingCity("Austin");
        checkoutPage.addBillingState("TX");
        checkoutPage.addBillingPostalCode("78704");
        checkoutPage.addBillingPhone("512-867-5309");
        checkoutPage.selectBillingCountry("USA");
        checkoutPage.clickShippingSameBillingCheckbox();

        // complete purchase
        TransactionResultsPage resultsPage = checkoutPage.clickPurchaseButton();

        // verify total price
        float shipping = resultsPage.getTotalShippingAsFloat();
        float expectedTotal = iphone4Price + shipping;
        float actualTotal = resultsPage.getTotalPriceAsFloat();
        assertEquals(expectedTotal, actualTotal, 0.001);

    }

    @Test
    public void testAccountDetailsUpdateIsSaved() throws Exception {

        driver.get(BASEURL);

        String username = "pgconreaux";
        String password = "XUBZAcJ5R1oN";

        // home page
        HomePage homePage = new HomePage(driver);

        // log in
        AccountPage accountPage = homePage.clickAccountIcon();
        accountPage.addUsername(username);
        accountPage.addPassword(password);
        accountPage.clickLoginButton();

        // change address
        accountPage.clickDetailsLink();
        int rand = ThreadLocalRandom.current().nextInt(1, 99 + 1);
        String newAddress = rand + " Sesame Street";
        accountPage.clearAddress();
        accountPage.addAddress(newAddress);
        accountPage.clickSaveProfile();

        // log out
        LoginPage loginPage = accountPage.clickLogoutLink();

        // log back in
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        ProfilePage profilePage = loginPage.clickLoginButton();

        // navigate to account details
        homePage = profilePage.clickHomeLink();
        accountPage = homePage.clickAccountIcon();
        accountPage.clickDetailsLink();

        // verify new address
        String address = accountPage.getAddress();
        assertEquals(newAddress, address);

    }

    @Test
    public void testRemoveAllItemsProducesEmptyCart() throws Exception {

        driver.get(BASEURL);

        HomePage homePage = new HomePage(driver);

        // select iphones product category
        IPhonesPage iPhonesPage = homePage.clickIphonesProductCategory();

        // add items to cart
        iPhonesPage.addMagicMouse();
        iPhonesPage.clickContinueShopping();
        iPhonesPage.addIphone4Black();
        iPhonesPage.clickContinueShopping();
        iPhonesPage.addIphone4White();

        // checkout
        CheckoutPage checkoutPage = iPhonesPage.clickCheckout();

        // remove items from cart
        int itemCount = 3;
        for (int i = 0; i < itemCount; i++) {
            checkoutPage.clickRemoveItem(0); // index 0 always selects the first row to remove
        }

        // verify no items left in cart
        int newCount = checkoutPage.getItemCountAsInt();
        assertEquals(0, newCount);

    }
}