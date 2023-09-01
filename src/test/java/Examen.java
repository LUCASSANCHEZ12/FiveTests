import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.Product;
import pages.YourCartPage;
import utilities.DriverManager;

import java.util.*;


public class Examen extends BaseTest {


    @Test
    public void verificarCompra(){
        //Loguearse con las siguientes credenciales: standard_user, secret_sauce
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        System.out.println("Login");
        //Agregar 2 o mas productos al carrito desde el home page
        //Ir al carrito
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.clickOnShoppingCartButton();
        System.out.println("Productos añadidos");
        YourCartPage cartPage = new YourCartPage(DriverManager.getDriver().driver);


        boolean val = true;
        //Verificar que los 2 items agregados estan en el carrito
        for (String element: homePage.AddedProducts()){
            val = cartPage.isProductDisplayed(element);
        }
        if (val){
            System.out.println("Productos agregados");
            List<Float> prices = new ArrayList<>();
            //Verificar que el precio sea correcto de los items agregados
            prices.add(29.99f);
            prices.add(9.99f);

            for (int i =0; i < 2; i++){
                val = cartPage.isPriceDisplayed(prices.get(i));
            }

            if (val){
                //Verificar que el icono del carrito tiene el numero correcto en base a cuantos items fueron seleccionados
                if (cartPage.cartCount() != 2){
                    System.out.println("Error en el icono del carrito");
                }

                //Remover los items agregados

                for (String element: homePage.AddedProducts()){
                    cartPage.removeProduct(element);
                }

                //Verificar que los items fueron removidos
                if (!cartPage.IsProductsListEmpty()){
                    System.out.println("Error al remover items");
                }

                //Verificar que el icono del carrito no tiene ningun numero

                if (cartPage.cartCount() == 0){
                    System.out.println("Error en el icono del carrito");
                }
            }
        }else{
            System.out.println("Error, no existe el producto");
        }
    }


}
