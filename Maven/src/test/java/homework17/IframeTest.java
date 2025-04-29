package homework17;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import utils.LoginToCourse;

import java.time.Duration;

@Test
public class IframeTest {
    public static void selectOptions(){
        WebDriver driver = DriverSetUp.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        LoginToCourse.login(driver, wait);

        Actions actions = new Actions(driver);
        LoginToCourse.waitForElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"), wait);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"))).perform();
        LoginToCourse.waitForElement(By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[3]"), wait);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[3]"))).click().build().perform();

        LoginToCourse.waitForElement(By.tagName("iframe"), wait);
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        driver.findElement(By.id("AlertButton")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alert1ButtonText = alert.getText();
        Assert.assertEquals(alert1ButtonText, "You have called alert!");
        alert.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]")));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText(),"Results:\nCongratulations, you have successfully enrolled in the course!", "We expected text: " + driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText() +
                "\nequals Results: Congratulations, you have successfully enrolled in the course!");

        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button")).click();
        actions.doubleClick(driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button"))).perform();
        String alert2ButtonText = alert.getText();
        Assert.assertEquals(alert2ButtonText, "Are you sure you want to apply the discount?");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText(),"Results:\nYou received a 10% discount on the second course.", "We expected text: " + driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText() +
                "\nequals Results: You received a 10% discount on the second course.");
        driver.findElement(By.cssSelector("[data-test-id=\"PromptButton\"]")).click();
        actions.contextClick(driver.findElement(By.cssSelector("[data-test-id=\"PromptButton\"]"))).perform();
        String alert3ButtonText = alert.getText();
        Assert.assertEquals(alert3ButtonText, "Here you may describe a reason why you are cancelling your registration (or leave this field empty).");
        alert.sendKeys("");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText(),"Results:\nYour course application has been cancelled. Reason: You did not notice any reason.", "We expected text: " + driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText() +
                "\nequals Results: Your course application has been cancelled. Reason: You did not notice any reason.");


    }
}
