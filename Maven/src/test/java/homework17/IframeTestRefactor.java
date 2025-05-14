package homework17;

import homework18.testCases.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import utils.UniversalListeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Listeners({UniversalListeners.class})
public class IframeTestRefactor {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static IframePage iframePage;

    public static void takeScreenshot(WebDriver driver, String methodName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        try {
            String screenshotPath = "target/allure-results/screenshot-" + methodName + ".png";
            FileUtils.copyFile(source, new File(screenshotPath));

            Allure.addAttachment("Screenshot for " + methodName, new FileInputStream(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @Description("Checking if clicking buttons in different ways causes alerts to pop-up")
    @Severity(SeverityLevel.CRITICAL)
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
