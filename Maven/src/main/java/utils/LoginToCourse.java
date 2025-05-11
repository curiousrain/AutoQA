package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.urls.Link;

public class LoginToCourse {
    public static WebElement waitForElement(By locator, WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void login(WebDriver driver, WebDriverWait wait) {
        driver.get(Link.ANDERSENCOURSE_LOGIN.getLink());

        waitForElement(By.name("email"), wait).sendKeys("aliceperkins@mail.com");
        waitForElement(By.name("password"), wait).sendKeys("96ALiceperkINS71");
        waitForElement(By.tagName("button"), wait).click();
    }
}
