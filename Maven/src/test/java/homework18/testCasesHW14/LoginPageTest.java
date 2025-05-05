package homework18.testCasesHW14;

import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverSetUp;

public class LoginPageTest {
    private static WebDriver driver;
    private static LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Test
    public void loginWithEmailAndPassword() {
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton()
                .checkUsername("Alice Perkins");
    }

    @Test
    public void loginWithoutEmail() {
        loginPage
                .openLogInPage()
                .fillEmail("")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton()
                .checkErrorMessageForEmail("Required");
    }

    @Test
    public void loginWithoutPassword() {
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("")
                .clickOnButton()
                .checkErrorMessageForPassword("Required");
    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("12345678")
                .clickOnButton()
                .checkErrorMessageForPassword("Email or password is not valid")
                .checkErrorMessageForEmail("Email or password is not valid");
    }

    @Test
    public void loginWithInvalidLogin() {
        loginPage
                .openLogInPage()
                .fillEmail("mymail@mail.com")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton()
                .checkErrorMessageForPassword("Email or password is not valid")
                .checkErrorMessageForEmail("Email or password is not valid");
    }
}
