package homework18.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.Assert;
import utils.urls.Link;

import java.time.Duration;
import java.util.regex.Pattern;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    private static WebDriver driver;
    private static WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @FindBy(name = "firstName")
    private static WebElement firstName;
    @FindBy (xpath = "/html/body/div/div/div/div/form/div/div[1]/div/span")
    private static WebElement firstNameErrorMessage;
    @FindBy (name = "lastName")
    private static WebElement lastName;
    @FindBy (xpath = "/html/body/div/div/div/div/form/div/div[2]/div/span")
    private static WebElement lastNameErrorMessage;
    @FindBy (name = "dateOfBirth")
    private static WebElement dateOfBirth;
    @FindBy (xpath = "/html/body/div/div/div/div/form/div/label/div[2]/span")
    private static WebElement dateOfBirthErrorMessage;
    @FindBy (name = "email")
    private static WebElement email;
    @FindBy (xpath = "/html/body/div/div/div/div/form/div/div[3]/div/span")
    private static WebElement emailErrorMessage;
    @FindBy (name = "password")
    private static WebElement password;
    @FindBy (xpath = "/html/body/div[1]/div/div/div/form/div/div[4]/div/span")
    private static WebElement passwordErrorMessage;
    @FindBy (name = "passwordConfirmation")
    private static WebElement passwordConfirmation;
    @FindBy (xpath = "/html/body/div/div/div/div/form/div/div[5]/div/span")
    private static WebElement passwordConfirmationErrorMessage;
    @FindBy (css = "button[type=\"submit\"]")
    private static WebElement button;
    By h1 = By.tagName("h1");

    public RegistrationPage openRegistrationPage() {
        driver.get(Link.ANDERSENCOURSE_REGISTRATION.getLink());
        logger.info("Page is opened");
        return this;
    }
    public RegistrationPage fillFirstName(String value) {
        sendKeys(firstName, value);
        logger.info("First name is filled");
        return this;
    }
    public RegistrationPage fillLastName(String value) {
        sendKeys(lastName, value);
        logger.info("Last name is filled");
        return this;
    }

    public RegistrationPage selectDateOfBirth(String value) {
        sendKeys(dateOfBirth, value);
        sendKeys(dateOfBirth, Keys.ENTER);
        logger.info("Date of birth is selected");
        return this;
    }

    public RegistrationPage fillEmail(String value) {
        sendKeys(email, value);
        logger.info("Email is filled");
        return this;
    }

    public RegistrationPage fillPassword(String value){
        sendKeys(password, value);
        logger.info("Password is filled");
        return this;
    }
    public RegistrationPage fillConfirmPassword(String value){
        sendKeys(passwordConfirmation, value);
        logger.info("Confirm Password is filled");
        return this;
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }

    public void sendKeys(WebElement locator, Keys key) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(key);
    }

    public RegistrationPage clickForButtonToBeClickable()  {
        wait.until(ExpectedConditions.elementToBeClickable(button));
        logger.info("Button is clickable");
        return this;
    }
    public RegistrationPage clickOnButton()  {
        wait.until(ExpectedConditions.visibilityOf(button)).click();
        logger.info("Button is clicked");
        return this;
    }
    public RegistrationPage checkh1(String title){
        wait.until(ExpectedConditions.textMatches(h1, Pattern.compile(title)));
        String actualText = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualText,title, "We expected text: " + actualText +
                "\nequals " + actualText);
        logger.info("User is registered");
        return this;
    }

    public RegistrationPage checkErrorMessageForFirstName(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(firstNameErrorMessage));
        String actualText = firstNameErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for first name is checked");
        return this;
    }

    public RegistrationPage checkErrorMessageForLastName(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(lastNameErrorMessage));
        String actualText = lastNameErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for last name is checked");
        return this;
    }

    public RegistrationPage checkErrorMessageForDayOfBirth(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthErrorMessage));
        String actualText = dateOfBirthErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for date of birth is checked");
        return this;
    }



    public RegistrationPage checkErrorMessageForEmail(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        String actualText = emailErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for email is checked");
        return this;
    }

    public RegistrationPage checkErrorMessageForPassword(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        String actualText = passwordErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for password is checked");
        return this;
    }

    public RegistrationPage checkErrorMessageForPasswordConformation(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordConfirmationErrorMessage));
        String actualText = passwordConfirmationErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for password conformation is checked");
        return this;
    }
}
