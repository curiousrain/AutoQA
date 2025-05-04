package homework17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverSetUp;
import utils.LoginToCourse;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Test
public class SelectTest {
    public void selectOptions() {
        WebDriver driver = DriverSetUp.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        LoginToCourse.login(driver, wait);
        Actions actions = new Actions(driver);
        LoginToCourse.waitForElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"), wait);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/main/div/section/div/div[2]/div[2]/div"))).perform();
        LoginToCourse.waitForElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[1]"), wait);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[1]"))).click().build().perform();
        Select countrySelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-lol=\"SelectCountry\"]"))));
        Select languageSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SelectLanguage"))));
        Select typeSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-doubtful-but-ok=\"SelectType\"]"))));
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyyy");
        driver.findElement(By.cssSelector("[data-calendar=\"1\"]")).sendKeys(nextMonday.format(dateFormat));
        LocalDate afterTwoWeek = nextMonday.plusWeeks(2);
        driver.findElement(By.cssSelector("[data-calendar=\"2\"]")).sendKeys(afterTwoWeek.format(dateFormat));
        Select multipleSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MultipleSelect"))));
        countrySelect.selectByVisibleText("USA");
        languageSelect.selectByVisibleText("English");
        typeSelect.selectByVisibleText("Testing");
        multipleSelect.selectByValue("AQA Python");
        multipleSelect.selectByValue("AQA Java");
        actions.moveToElement(driver.findElement(By.tagName("button"))).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/main/section/h2")));
        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(),"Unfortunately, we did not find any courses matching your chosen criteria.", "We expected text: " + driver.findElement(By.tagName("h2")).getText() +
        "\nequals Unfortunately, we did not find any courses matching your chosen criteria");
        driver.quit();
    }
}
