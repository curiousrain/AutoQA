package homework17;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static utils.LoginToCourse.waitForElement;

public class IframePage {

    public IframePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    protected Logger logger = LogManager.getLogger(this.getClass());

    By AQA_Practice = By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div");
    By iFramePage = By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[3]");
    By iFrame = By.tagName("iframe");
    By alert = By.id("AlertButton");
    By resultText = By.xpath("/html/body/div/div/div[2]");
    By doubleClickButton = By.xpath("/html/body/div/div/div[1]/div[2]/button");
    By contextClickButton = By.cssSelector("[data-test-id=\"PromptButton\"]");

    public IframePage selectIframePage() {
        waitForElement(AQA_Practice, wait);
        actions.moveToElement(driver.findElement(AQA_Practice)).perform();
        logger.info("Option is selected");
        return this;
    }
    public IframePage openIframePage() {
        waitForElement(iFramePage, wait);
        actions.moveToElement(driver.findElement(iFramePage)).click().build().perform();
        logger.info("The page is opened");
        return this;
    }

    public IframePage clickOnIframeButton() {
        waitForElement(iFrame, wait);
        WebElement iframe = driver.findElement(iFrame);
        driver.switchTo().frame(iframe);
        return this;
    }

    public IframePage clickOnAlertButton() {
        driver.findElement(alert).click();
        logger.info("Alert button is clicked");
        return this;
    }

    public IframePage checkAlertText(String expectedText) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alert1ButtonText = alert.getText();
        Assert.assertEquals(alert1ButtonText, expectedText);
        alert.accept();
        logger.info("Alert message is appeared");
        return this;
    }

    public IframePage checkResultsText(String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultText));
        Assert.assertEquals(driver.findElement(resultText).getText(),expectedText, "We expected text: " + driver.findElement(resultText).getText() +
                "\nequals "+ expectedText);
        logger.info("Result message is appeared");
        return this;
    }

    public IframePage clickOnDoubleClickAlert() {
        driver.findElement(doubleClickButton).click();
        actions.doubleClick(driver.findElement(doubleClickButton)).perform();
        logger.info("Double click");
        return this;
    }
    public IframePage clickOnContextClickAlert() {
        driver.findElement(contextClickButton).click();
        actions.contextClick(driver.findElement(contextClickButton)).perform();
        logger.info("Context click");
        return this;
    }

    public IframePage fillPrompt(String expected) {
        Alert alert = driver.switchTo().alert();
        String alert3ButtonText = alert.getText();
        Assert.assertEquals(alert3ButtonText, expected);
        alert.sendKeys("");
        alert.accept();
        logger.info("The prompt is filled");
        return this;
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }

}
