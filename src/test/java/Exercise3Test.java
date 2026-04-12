import org.testng.annotations.*;

public class Exercise3Test {
    @BeforeGroups("Smoke")
    public void beforeSmoke() {
        System.out.println("Starting Smoke Tests...");
    }

    @AfterGroups("Smoke")
    public void afterSmoke() {
        System.out.println("Finished Smoke Tests...");
    }

    @BeforeGroups("Regression")
    public void beforeRegression() {
        System.out.println("Starting Regression Tests...");
    }

    @AfterGroups("Regression")
    public void afterRegression() {
        System.out.println("Finished Regression Tests...");
    }

    @Test(groups = {"Smoke"})
    public void testHomepageLoads() {}

    @Test(groups = {"Smoke"})
    public void testLoginPageVisible() {}

    @Test(groups = {"Smoke"})
    public void testFooterLinks() {}

    @Test(groups = {"Regression"})
    public void testLoginValidCreds() {}

    @Test(groups = {"Regression"})
    public void testLoginInvalidCreds() {}

    @Test(groups = {"Regression"})
    public void testPasswordReset() {}

    @Test(groups = {"Regression"})
    public void testAccountBalance() {}
}
