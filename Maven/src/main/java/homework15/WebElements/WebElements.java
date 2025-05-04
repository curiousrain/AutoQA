package homework15.WebElements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElements {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement a = driver.findElement(By.tagName("h1"));
        WebElement b = driver.findElement(By.cssSelector("#root > div > div > div > form > a > span"));
        compareElements(a,b);
        driver.quit();
    }

    public static void compareElements(WebElement a, WebElement b) {
        Rectangle aRect = a.getRect();
        Rectangle bRect = b.getRect();
        if (a.getLocation().getY() > b.getLocation().getY()) {
            System.out.println("Element b is higher than element a ");
        } else {
            System.out.println("Element a is higher than element b");
        }
        if (a.getLocation().getX() > b.getLocation().getX()) {
            System.out.println("Element b is more on the left than element a ");
        } else {
            System.out.println("Element a is more on the left than element b");
        }
        if (aRect.height * aRect.width > bRect.height * bRect.width) {
            System.out.println("Element a is bigger than element b");
        } else {
            System.out.println("Element b is bigger than element a");
        }

    }
}
