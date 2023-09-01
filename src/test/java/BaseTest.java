import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import utilities.DriverManager;

public class BaseTest {

    @BeforeEach
    public void setup(){
        DriverManager.getDriver().driver.get("https://www.saucedemo.com/");
        DriverManager.getDriver().driver.manage().window().maximize();
    }

    @AfterAll
    public static void cleanUp(){
        DriverManager.getDriver().driver.close();
    }
}
