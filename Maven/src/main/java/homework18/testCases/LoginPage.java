package homework18.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.urls.Link;


import java.time.Duration;
import java.util.regex.Pattern;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    private final WebDriver driver;
    private final WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @FindBy (name = "email")
    private static WebElement email;

    @FindBy (name = "password")
    private static WebElement password;

    @FindBy (tagName = "button")
    private static WebElement button;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div/div[1]/div/span")
    private static WebElement emailErrorMessage;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div/div[2]/div/span")
    private static WebElement passwordErrorMessage;

    private static final By h1 = By.tagName("h1");
    public LoginPage openLogInPage() {
        driver.get(Link.ANDERSENCOURSE_LOGIN.getLink());
        logger.info("Page is opened");
        return this;
    }

    public LoginPage fillEmail(String value) {
        sendKeys(email, value);
        logger.info("Email is filled");
        return this;
    }

    public LoginPage fillPassword(String value){
        sendKeys(password, value);
        logger.info("Password is filled");
        return this;
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }

    public LoginPage clickOnButton() {
        wait.until(ExpectedConditions.visibilityOf(button)).click();
        logger.info("Button is clicked");
        return this;
    }

    public LoginPage checkUsername(String username){
        wait.until(ExpectedConditions.textMatches(h1, Pattern.compile(username)));
        String actualText = driver.findElement(h1).getText();

        Assert.assertEquals(actualText,username, "We expected text: " + actualText +
                "\nequals " + actualText);
        logger.info("Username is checked");

        return this;
    }

    public LoginPage checkErrorMessageForEmail(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        String actualText = emailErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for email is checked");
        return this;
    }

    public LoginPage checkErrorMessageForPassword(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        String actualText = passwordErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
        logger.info("Error message for password is checked");
        return this;
    }

    public LoginPage checkH1(String expectedText) {
        wait.until(ExpectedConditions.textMatches(h1, Pattern.compile(expectedText)));
        String actualText = driver.findElement(h1).getText();

        Assert.assertEquals(actualText,expectedText, "We expected text: " + actualText +
                "\nequals " + expectedText);
        logger.info("Username is same as it was");
        return this;
    }
}
