package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourCartPage {
    WebDriver driver;
    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;


    @FindBy(className = "inventory_item_price")
    List<WebElement> ProductsPrice;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCart;

    @FindBy(className = "cart_list")
    List<WebElement> cartList;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String product){
        for (WebElement element: productNames) {
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }
    public Integer cartCount(){ return Integer.parseInt(shoppingCart.getText()); }
    public void removeProduct(String product){
        //sauce-labs-bike-bight
        //remove-
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    public boolean shoppingCartCountIsEnabled(String count){
        if (shoppingCart.getText().equalsIgnoreCase(count)){
            return true;
        }else{
            return false;
        }
    }

    public boolean IsProductsListEmpty() {
        if (cartList.size() == 0){
            return  true;
        }else{
            return false;
        }
    }
    public boolean isPriceDisplayed(float price){
        for (WebElement element: ProductsPrice) {
            String priceText = element.getText().replaceAll("[^0-9.]", "");
            float priceValue = Float.parseFloat(priceText);
            System.out.println(priceValue);
            if(priceValue == price){
                return true;
            }
        }
        return false;
    }
}
