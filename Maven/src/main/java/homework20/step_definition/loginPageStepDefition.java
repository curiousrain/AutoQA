package homework20.step_definition;

import homework18.testCases.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.urls.Link;

import java.time.Duration;
import java.util.regex.Pattern;

public class loginPageStepDefition {
    private static WebDriver driver;
    private static WebDriverWait wait;


    By email = By.name("email");
    By password = By.name("password");
    By button = By.tagName("button");
    By emailErrorMessage = By.xpath("/html/body/div/div/div/div/form/div/div[1]/div/span");
    By passwordErrorMessage = By.xpath("/html/body/div/div/div/div/form/div/div[2]/div/span");
    By checkUsername = By.tagName("h1");

    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void checkUsernameMethod(String text){
        wait.until(ExpectedConditions.textMatches(checkUsername, Pattern.compile(text)));
        String actualText = driver.findElement(checkUsername).getText();

        Assert.assertEquals(actualText,text, "We expected text: " + actualText +
                "\nequals " + actualText);

    }
    public void checkErrorMessageForEmailMethod(String errorMessage) {
        wait.until(ExpectedConditions.textMatches(emailErrorMessage, Pattern.compile(String.valueOf(errorMessage))));
        String actualText = driver.findElement(emailErrorMessage).getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
    }

    public void checkErrorMessageForPasswordMethod(String errorMessage) {
        wait.until(ExpectedConditions.textMatches(passwordErrorMessage, Pattern.compile(String.valueOf(errorMessage))));
        String actualText = driver.findElement(passwordErrorMessage).getText();

        Assert.assertEquals(actualText, errorMessage, "We expected text: " + actualText +
                "\nequals " + errorMessage);
    }
    @Given("Set up driver")
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @When("Opening Login Page")
    public void openingLoginPage() {
        driver.get(Link.ANDERSENCOURSE_LOGIN.getLink());
    }
    @And("set email {string}")
    public void fillEmail(String text) {
        sendKeys(email, text);
    }
    @And("set password {string}")
    public void fillPassword(String text) {
        sendKeys(password, text);
    }
    @And("click on sign in button")
    public void clickOnButton() {
        driver.findElement(button).click();
    }
    @Then("check username {string}")
    public void checkUsername(String text) {
        checkUsernameMethod(text);
    }
    @Then("check error message for email {string}")
    public void checkErrorMessageForEmail(String errorMessage) {
        checkErrorMessageForEmailMethod(errorMessage);
    }
    @Then("check error message for password {string}")
    public void checkErrorMessageForPassword(String errorMessage) {
        checkErrorMessageForPasswordMethod(errorMessage);
    }
    @Then("quit driver")
    public void quit_driver() {
        driver.quit();
    }
}
