package homework17;

import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverSetUp;


public class IframeTestRefactor {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static IframePage iframePage;
    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        loginPage = new LoginPage(driver);
        iframePage = new IframePage(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
    @Test
    public static void iFrame(){
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton()
                .checkUsername("Alice Perkins");

        iframePage
                .selectIframePage()
                .openIframePage()
                .clickOnIframeButton()
                .clickOnAlertButton()
                .checkAlertText("You have called alert!")
                .checkResultsText("Results:\nCongratulations, you have successfully enrolled in the course!")
                .clickOnDoubleClickAlert()
                .checkAlertText("Are you sure you want to apply the discount?")
                .checkResultsText("Results:\nYou received a 10% discount on the second course.")
                .clickOnContextClickAlert()
                .fillPrompt("Here you may describe a reason why you are cancelling your registration (or leave this field empty).")
                .checkResultsText("Results:\nYour course application has been cancelled. Reason: You did not notice any reason.");

    }
}
