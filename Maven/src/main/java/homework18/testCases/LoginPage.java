package homework18.testCases;

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

    private WebDriver driver;
    private WebDriverWait wait;

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

    By h1 = By.tagName("h1");

    public LoginPage openLogInPage() {
        driver.get(Link.ANDERSENCOURSE_LOGIN.getLink());
        return this;
    }

    public LoginPage fillEmail(String value) {
        sendKeys(email, value);
        return this;
    }

    public LoginPage fillPassword(String value){
        sendKeys(password, value);
        return this;
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }

    public LoginPage clickOnButton() {
        wait.until(ExpectedConditions.visibilityOf(button)).click();
        return this;
    }

    public LoginPage checkUsername(String username){
        wait.until(ExpectedConditions.textMatches(h1, Pattern.compile(username)));
        String actualText = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualText,username, "We expected text: " + actualText +
                "\nequals " + actualText);

        return this;
    }

    public LoginPage checkErrorMessageForEmail(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        String actualText = emailErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);

        return this;
    }

    public LoginPage checkErrorMessageForPassword(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        String actualText = passwordErrorMessage.getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);

        return this;
    }
}
