package homework16.Parameters;


import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import java.time.Duration;
public class ParametersTestRefactor {
    static WebDriver driver;
    static WebDriverWait wait;
    private static LoginPage loginPage;
    @BeforeClass
    public static void WebDriver() {
         driver = DriverSetUp.getDriver();
         wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void closeDriver(){
        driver.quit();
    }

    @Parameters({"email", "username"})
    @Test
    public static void parametersForLogin(String email,String username) {
        loginPage
                .openLogInPage()
                .fillEmail(email)
                .fillPassword("12345678")
                .clickOnButton()
                .checkH1(username);
    }
}
