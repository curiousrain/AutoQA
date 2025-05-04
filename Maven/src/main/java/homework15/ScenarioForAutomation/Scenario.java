package homework15.ScenarioForAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Scenario {
    public static void main(String[] args) throws InterruptedException {
        File uploadFile = new File("C:\\Users\\nonea\\Desktop\\image\\cat.jpg");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");

        Thread.sleep(2000);

        driver.findElement(By.name("email")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("email")).sendKeys("aliceperkins@mail.com");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("96ALiceperkINS71");
        Thread.sleep(2000);
        driver.findElement(By.tagName("button")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d-> d.findElement(By.cssSelector("input[type=file]")));
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(uploadFile.getAbsolutePath());
        Thread.sleep(2000);
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
