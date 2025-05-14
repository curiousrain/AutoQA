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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Listeners({UniversalListeners.class})
public class SelectPageTestRefactor {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static SelectPage selectPage;

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
        selectPage = new SelectPage(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Description("Checking if there's courses that suits selected options by user")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void selectOptions() {

        loginPage
            .openLogInPage()
            .fillEmail("aliceperkins@mail.com")
            .fillPassword("96ALiceperkINS71")
            .clickOnButton()
            .checkUsername("Alice Perkins");

        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate afterTwoWeek = nextMonday.plusWeeks(2);

        selectPage
            .chooseSelectPage()
            .openSelectPage()
            .selectOptions(SelectType.COUNTRY, "USA")
            .selectOptions(SelectType.LANGUAGE, "English")
            .selectOptions(SelectType.TYPE, "Testing")
            .selectOptions(SelectType.MULTIPLE_SELECT, "AQA Python")
            .selectOptions(SelectType.MULTIPLE_SELECT, "AQA Java")
            .setCalendarField(CalendarField.FROM, nextMonday)
            .setCalendarField(CalendarField.TO, afterTwoWeek)
            .pressButton()
            .checkH2("Unfortunately, we did not find any courses matching your chosen criteria.");
    }
}
