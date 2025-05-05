package homework16.DataProvider;

import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import java.time.Duration;

public class DataProviderTestRefactor {
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

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"mailone@mail.com", "MailOne MailOne"},
                {"mailtwo@mail.com", "MailTwo MailTwo"},
                {"mailthree@mail.com", "MailThree MailThree"},
        };
    }
    @Test(dataProvider = "testData")
    public static void parametersForLogin(String email,String username) {
        loginPage
                .openLogInPage()
                .fillEmail(email)
                .fillPassword("12345678")
                .clickOnButton()
                .checkH1(username);
    }

}
