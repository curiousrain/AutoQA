package homework21;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDateTime;

public class AndroidApplicationTest {
    AppiumDriver driver;
    AndroidApplicationPage androidAppPage;


    @BeforeMethod
    public void setUp(){
        driver = new AppiumDriverInit().getDriver();
        androidAppPage = new AndroidApplicationPage(driver);
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }

    @Test
    public void countButtons() {
        androidAppPage.clickOnView();
        int count = androidAppPage.countButtons();
        Assert.assertEquals(count, 42);
    }

    @Test
    public void setDate(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1).withHour(23).withMinute(11);

        androidAppPage.clickOnView();
        androidAppPage.clickOnDateWidgets();
        androidAppPage.clickOnDialog();
        androidAppPage.clickOnChangeTheDate();

        androidAppPage.pickTheDate(tomorrow.toLocalDate());
        androidAppPage.clickOnChangeTheTimeSpinner();
        androidAppPage.pickTheTimeSpinner(11, 11);
        androidAppPage.checkDateValue(tomorrow);
    }
    @Test
    public void tapNext(){
        androidAppPage.clickOnView();
        androidAppPage.scrollUntilTextSwitcherAndClick();
        androidAppPage.tapNextButton();
    }
}
