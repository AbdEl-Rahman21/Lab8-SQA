import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Exercise2Test {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();

            if (System.getenv("CI") != null) {
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
            }

            driver = new EdgeDriver(options);
        } else {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();

            if (System.getenv("CI") != null) {
                options.addArguments("--headless");
            }

            driver = new FirefoxDriver(options);
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

        String url = driver.getCurrentUrl();

        driver.navigate().back();

        if (isValid) {
            Assert.assertNotNull(url);
            Assert.assertTrue(url.contains("success"));
        } else {
            // This will never work (There is no invalid case)
            Assert.assertNotNull(url);
            Assert.assertTrue(url.contains("Invalid"));
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
