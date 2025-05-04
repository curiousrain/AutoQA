package homework16.DataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import utils.LoginToCourse;

import java.time.Duration;
import java.util.regex.Pattern;

public class DataProviderTest {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeClass
    public static void WebDriver() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
    public static void parametersForLogin(String email, String username) {
        LoginToCourse.login(driver, wait, email, "12345678");
        wait.until(ExpectedConditions.textMatches(By.tagName("h1"), Pattern.compile(username)));
        String actualText = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualText,username, "We expected text: " + actualText +
                "\nequals " + actualText);
    }

}
