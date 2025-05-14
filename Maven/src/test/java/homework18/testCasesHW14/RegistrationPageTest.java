package homework18.testCasesHW14;

import homework18.testCases.RegistrationPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverSetUp;
import utils.UniversalListeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Listeners({UniversalListeners.class})
public class RegistrationPageTest {
    private static WebDriver driver;
    private static RegistrationPage registrationPage;

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
    @AfterMethod
    public void makeScreenshotIfTestFailed(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            takeScreenshot(DriverSetUp.getDriver(), result.getMethod().getMethodName());
        }
    }

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }


    @Description("Registration with all filled fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registrationWithAllFilledFields() {
        registrationPage
                .openRegistrationPage()
                .fillFirstName("FirstNameFirst")
                .fillLastName("FirstNameLast")
                .selectDateOfBirth("12/01/2025")
                .fillEmail("new12345@mail.com")
                .fillPassword("12345678")
                .fillConfirmPassword("12345678")
                .clickForButtonToBeClickable()
                .clickOnButton()
                .checkh1("Sign In");
    }

    @Description("Registration without filling fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registrationWithoutFilling() {
        registrationPage
                .openRegistrationPage()
                .clickOnButton()
                .checkErrorMessageForFirstName("Required")
                .checkErrorMessageForLastName("Required")
                .checkErrorMessageForDayOfBirth("Required")
                .checkErrorMessageForEmail("Required")
                .checkErrorMessageForPassword("Required")
                .checkErrorMessageForPasswordConformation("Required");

    }

    @Description("Registration with filling all fields in Cyrillic")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registrationWithFillingInCyrillic() {
        registrationPage
                .openRegistrationPage()
                .fillFirstName("Имя")
                .fillLastName("Фамилия")
                .selectDateOfBirth("12/01/2025")
                .fillEmail("почта@почта.ру")
                .fillPassword("пароль12")
                .fillConfirmPassword("пароль12")
                .clickOnButton()
                .checkErrorMessageForEmail("Invalid email address");
    }

    @Description("Registration with filling only Fist Name and Last Name fields in Cyrillic")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registrationWithFillingNamesInCyrillic() {
        registrationPage
                .openRegistrationPage()
                .fillFirstName("Имя")
                .fillLastName("Фамилия")
                .selectDateOfBirth("12/01/2025")
                .fillEmail("cyrillicmail@mail.com")
                .fillPassword("пароль12")
                .fillConfirmPassword("пароль12")
                .clickForButtonToBeClickable()
                .clickOnButton()
                .checkh1("Sign In");
    }


    @Description("Registration with different password in Password Conformation field")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registrationWithDifferentPasswordInConformation() {
        registrationPage
                .openRegistrationPage()
                .fillFirstName("sdfdsfsd")
                .fillLastName("sdfdsfsd")
                .selectDateOfBirth("12/01/2025")
                .fillEmail("sfdfsdfs@mail.com")
                .fillPassword("пароль12")
                .fillConfirmPassword("пароль23")
                .clickOnButton()
                .checkErrorMessageForPasswordConformation("Passwords must match");
    }

}
