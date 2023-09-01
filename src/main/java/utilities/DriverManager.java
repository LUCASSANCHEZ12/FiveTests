package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static DriverManager instance;
    public WebDriver driver;

    private DriverManager(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public static DriverManager getDriver(){
        if(instance==null){
            instance = new DriverManager();
        }
        return instance;
    }
}
