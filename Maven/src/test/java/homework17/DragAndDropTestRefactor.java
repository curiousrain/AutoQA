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
public class DragAndDropTestRefactor {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static DragAndDrop dragAndDrop;

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
        dragAndDrop = new DragAndDrop(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Description("Checking drag and drop function")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void DragAndDrop() {
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton();
        dragAndDrop.selectDragAndDropPage()
                .openDragAndDropPage()
                .performDragAndDropTask()
                .checkSuccessMessage("Congratulations! Let's test for the best!");

    }
}