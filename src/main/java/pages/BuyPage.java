package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyPage {
    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkoutbutton;

    @FindBy(id = "continue")
    WebElement continuebutton;

    @FindBy(name = "finish")
    WebElement  finishbutton;

    @FindBy(id = "back-to-products")
    WebElement  backbutton;

    @FindBy(id = "first-name")
    WebElement nameTextBox;

    @FindBy(id = "last-name")
    WebElement lastnameTextBox;

    @FindBy(id = "postal-code")
    WebElement postalcodeTextBox;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement loginErrorMessage;

    public BuyPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isErrorTextDisplayed(String error){
        String actualErrorMessage = loginErrorMessage.getText();
        if(error.equalsIgnoreCase(actualErrorMessage)){
            return true;
        } else {
            return false;
        }
    }
    public void clickCheckoutButton(){ checkoutbutton.click(); }

    public void clickContinueButton(){ continuebutton.click(); }

    public void clickFinishButton(){ finishbutton.click(); }

    public void clickBackHomeButton(){ backbutton.click(); }
    public void setNameTextBox(String userName){
        nameTextBox.sendKeys(userName);
    }
    public void setLastNameTextBox(String userName){
        lastnameTextBox.sendKeys(userName);
    }
    public void setPostalcodeTextBox(String userName){
        postalcodeTextBox.sendKeys(userName);
    }

}
