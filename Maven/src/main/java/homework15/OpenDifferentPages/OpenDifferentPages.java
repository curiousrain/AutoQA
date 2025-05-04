package homework15.OpenDifferentPages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenDifferentPages {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String[] urls = {
            "http://www.automationpractice.pl/index.php",
            "https://zoo.waw.pl/",
            "https://www.w3schools.com/",
            "https://www.clickspeedtester.com/click-counter/",
            "https://andersenlab.com/"
        };
        driver.manage().window().maximize();
        String handle = driver.getWindowHandle();
        for (String url:urls) {
            driver.switchTo().newWindow(WindowType.WINDOW);
            driver.get(url);
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(d-> d.findElement(By.tagName("title")));
            String title = driver.getTitle();
            System.out.printf("title:%s, url:%s\n", title, url );
            if (title.contains("Zoo")) {
                driver.close();
                driver.switchTo().window(handle);
            }
        }
        driver.quit();
    }

}
