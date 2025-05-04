package TestCasesFromHW14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginWithAllCredentials {
    public static void main(String[] args) throws InterruptedException {
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
        driver.quit();
    }
}
