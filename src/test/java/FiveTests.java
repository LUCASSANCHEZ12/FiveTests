import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utilities.DriverManager;

import java.time.Duration;
import java.util.Set;

public class FiveTests extends BaseTest {

    @Test
    public void FinishSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.clickOnShoppingCartButton();

        BuyPage buyPage = new BuyPage(DriverManager.getDriver().driver);
        buyPage.clickCheckoutButton();
        buyPage.setNameTextBox("Lucas");
        buyPage.setLastNameTextBox("Sanchez Salinas");
        buyPage.setPostalcodeTextBox("000");
        Thread.sleep(2000);

        buyPage.clickContinueButton();
        Thread.sleep(2000);

        buyPage.clickFinishButton();
        Thread.sleep(2000);
        buyPage.clickBackHomeButton();
    }


    @Test
    public void RemoveProductFromCartPageTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.clickOnShoppingCartButton();

        YourCartPage cartPage = new YourCartPage(DriverManager.getDriver().driver);
        cartPage.removeProduct("Sauce Labs Backpack");

        Assertions.assertTrue(cartPage.shoppingCartCountIsEnabled(""));

        Thread.sleep(2000);

    }

    @Test
    public void VerifyProductsTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        //lista de productos
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();

        YourCartPage cartPage = new YourCartPage(DriverManager.getDriver().driver);

        boolean val = true;

        for (String element: homePage.AddedProducts()){
            val = cartPage.isProductDisplayed(element);
            if(!val){
                System.out.println("Error, producto fuera de la lista");
            }
        }
        if (val){
            System.out.println("Correcto");
        }else{
            System.out.println("Error, producto fuera de la lista");
        }
        Thread.sleep(5000);
    }

    @Test
    public void verifyCartCountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());
        homePage.addProductToCart("Sauce Labs Backpack");

        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled("1"));
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled("2"));
        homePage.addProductToCart("Sauce Labs Bike Light");
        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled("3"));
        Thread.sleep(2000);

        homePage.removeProductFromCart("Sauce Labs Backpack");
        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled("2"));
        homePage.removeProductFromCart("Sauce Labs Bolt T-Shirt");
        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled("1"));
        homePage.removeProductFromCart("Sauce Labs Bike Light");
        Assertions.assertTrue(homePage.shoppingCartCountIsEnabled(""));
        Thread.sleep(2000);
    }

    @Test
    public void FinishErrorTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.clickOnShoppingCartButton();

        BuyPage buyPage = new BuyPage(DriverManager.getDriver().driver);
        buyPage.clickCheckoutButton();
        buyPage.setNameTextBox("Lucas");
        buyPage.clickContinueButton();
        buyPage.isErrorTextDisplayed("Error: Last Name is required");
        Thread.sleep(1500);
        buyPage.setLastNameTextBox("Sanchez Salinas");
        buyPage.clickContinueButton();
        buyPage.isErrorTextDisplayed("Error: Postal Code is required");
        Thread.sleep(1500);
        buyPage.setPostalcodeTextBox("000");
        buyPage.clickContinueButton();
        Thread.sleep(2000);
    }

    @Test
    public void verifyTwitterLink() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnTwitterButton();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String actualwindow = DriverManager.getDriver().driver.getWindowHandle();
        Set<String>windows = DriverManager.getDriver().driver.getWindowHandles();
        windows.remove(actualwindow);
        DriverManager.getDriver().driver.switchTo().window(windows.iterator().next());

        String currentURL = DriverManager.getDriver().driver.getCurrentUrl();
        Assertions.assertEquals("https://twitter.com/saucelabs", currentURL);
    }

    @Test
    public void verifyFacebookLink() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnFacebookButton();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String actualwindow = DriverManager.getDriver().driver.getWindowHandle();
        Set<String>windows = DriverManager.getDriver().driver.getWindowHandles();
        windows.remove(actualwindow);
        DriverManager.getDriver().driver.switchTo().window(windows.iterator().next());

        String currentURL = DriverManager.getDriver().driver.getCurrentUrl();
        Assertions.assertEquals("https://www.facebook.com/saucelabs", currentURL);
    }

    @Test
    public void verifyLinkedInLink() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnLinkedInButton();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String actualwindow = DriverManager.getDriver().driver.getWindowHandle();
        Set<String>windows = DriverManager.getDriver().driver.getWindowHandles();
        windows.remove(actualwindow);
        DriverManager.getDriver().driver.switchTo().window(windows.iterator().next());

        String currentURL = DriverManager.getDriver().driver.getCurrentUrl();
        Assertions.assertEquals("https://www.linkedin.com/company/sauce-labs/", currentURL);
    }
}