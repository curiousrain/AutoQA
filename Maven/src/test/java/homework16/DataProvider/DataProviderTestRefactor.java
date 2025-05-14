package homework16.DataProvider;

import homework18.testCases.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.DriverSetUp;
import utils.UniversalListeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
@Listeners({UniversalListeners.class})
public class DataProviderTestRefactor {
    static WebDriver driver;
    static WebDriverWait wait;
    private static LoginPage loginPage;

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

    @Description("Checking if users are logged in")
    @Severity(SeverityLevel.CRITICAL)
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
