package homework17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import utils.LoginToCourse;

import java.time.Duration;

@Test
public class DragAndDropTest {
    public void selectOptions() {
        WebDriver driver = DriverSetUp.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        LoginToCourse.login(driver, wait);
        Actions actions = new Actions(driver);
        LoginToCourse.waitForElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"), wait);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"))).perform();
        LoginToCourse.waitForElement(By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[2]"), wait);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[2]"))).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/main/section/section/div[2]/section[1]/h3")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/main/section/section/div[2]/section[2]/h3")));
        actions.pause(Duration.ofSeconds(2))
                .dragAndDrop(driver.findElement(By.id("manual1")), driver.findElement(By.id("target-manual1")))
                .dragAndDrop(driver.findElement(By.id("manual2")), driver.findElement(By.id("target-manual2")))
                .dragAndDrop(driver.findElement(By.id("auto1")), driver.findElement(By.id("target-auto1")))
                .dragAndDrop(driver.findElement(By.id("auto2")), driver.findElement(By.id("target-auto2")))
                .build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/main/section/div")));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/main/section/div")).getText(),"Congratulations! Let's test for the best!", "We expected text: " + driver.findElement(By.xpath("/html/body/div/div/div[2]/div/main/section/div")).getText() +
                "\nequals Congratulations! Let's test for the best!");
    }
}
