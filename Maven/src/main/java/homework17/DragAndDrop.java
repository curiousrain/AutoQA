package homework17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.Duration;

import static utils.LoginToCourse.waitForElement;

public class DragAndDrop {
    public DragAndDrop(WebDriver driver) {
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
    By dragAndDrop = By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[2]");
    By manual = By.xpath("/html/body/div/div/div[2]/div/main/section/section/div[2]/section[1]/h3");
    By automation = By.xpath("/html/body/div/div/div[2]/div/main/section/section/div[2]/section[2]/h3");
    By manual1 = By.id("manual1");
    By manual2 = By.id("manual2");
    By target_manual1 = By.id("target-manual1");
    By target_manual2 = By.id("target-manual2");
    By auto1 = By.id("auto1");
    By auto2 = By.id("auto2");
    By target_auto1 = By.id("target-auto1");
    By target_auto2 = By.id("target-auto2");
    By success_message = By.xpath("/html/body/div/div/div[2]/div/main/section/div");

    public DragAndDrop selectDragAndDropPage() {
        waitForElement(AQA_Practice, wait);
        actions.moveToElement(driver.findElement(AQA_Practice)).perform();
        return this;
    }

    public DragAndDrop openDragAndDropPage() {
        waitForElement(dragAndDrop, wait);
        actions.moveToElement(driver.findElement(dragAndDrop)).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manual));
        wait.until(ExpectedConditions.visibilityOfElementLocated(automation));
        logger.info("The page is opened");
        return this;
    }

    public DragAndDrop performDragAndDropTask() {
        actions.pause(Duration.ofSeconds(2))
                .dragAndDrop(driver.findElement(manual1), driver.findElement(target_manual1))
                .dragAndDrop(driver.findElement(manual2), driver.findElement(target_manual2))
                .dragAndDrop(driver.findElement(auto1), driver.findElement(target_auto1))
                .dragAndDrop(driver.findElement(auto2), driver.findElement(target_auto2))
                .build()
                .perform();
        logger.info("The task is performed");
        return this;
    }


    public DragAndDrop checkSuccessMessage(String successMessage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(success_message));
        Assert.assertEquals(driver.findElement(success_message).getText(),"Congratulations! Let's test for the best!", "We expected text: " + driver.findElement(success_message).getText() +
                "\nequals Congratulations! Let's test for the best!");
        logger.info("Success message is checked");
        return this;
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }


}
