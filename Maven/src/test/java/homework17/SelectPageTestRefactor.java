package homework17;

import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SelectPageTestRefactor {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static SelectPage selectPage;

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
