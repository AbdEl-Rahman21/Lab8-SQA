import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Objects;

public class Exercise2Test {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();
        }

        driver.get("https://demo.guru99.com/test/login.html");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
                { "test@mail.com", "123", false },
                { "test@mail.com", "152", true }
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, boolean isValid) {
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();

        if (isValid) {
            Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("success"));
        } else {
            // This will never work
            Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Invalid"));
        }

        driver.navigate().back();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
