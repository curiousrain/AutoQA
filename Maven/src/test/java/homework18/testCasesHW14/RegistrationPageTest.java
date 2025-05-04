package homework18.testCasesHW14;

import homework18.testCases.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverSetUp;

public class RegistrationPageTest {
    private static WebDriver driver;
    private static RegistrationPage registrationPage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }



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
