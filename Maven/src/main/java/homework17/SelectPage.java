package homework17;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utils.LoginToCourse.waitForElement;

public class SelectPage {
    private static final By h2 = By.xpath("/html/body/div/div/div[2]/div/main/section/h2");
    private static final By AQA_Practice = By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div");
    private static final By select = By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/section/div/div[2]/div[2]/div[2]/div[1]");
    private static final By countrySelect = By.cssSelector("[data-lol=\"SelectCountry\"]");
    private static final By languageSelect = By.id("SelectLanguage");
    private static final By typeSelect = By.cssSelector("[data-doubtful-but-ok=\"SelectType\"]");
    private static final By multipleSelect = By.id("MultipleSelect");
    private static final By calendarFrom = By.cssSelector("[data-calendar=\"1\"]");
    private static final By calendarTo = By.cssSelector("[data-calendar=\"2\"]");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final By BUTTON = By.tagName("button");

    public SelectPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    protected Logger logger = LogManager.getLogger(this.getClass());

    public SelectPage chooseSelectPage() {
        waitForElement(AQA_Practice, wait);
        actions.moveToElement(driver.findElement(AQA_Practice)).perform();
        return this;
    }

    public SelectPage openSelectPage() {
        waitForElement(select, wait);
        actions.moveToElement(driver.findElement(select)).click().build().perform();
        return this;
    }

    public SelectPage selectOptions(SelectType option, String value) {
        By selectLocator = null;

        switch (option) {
            case COUNTRY:
                selectLocator = countrySelect;
                break;
            case LANGUAGE:
                selectLocator = languageSelect;
                break;
            case TYPE:
                selectLocator = typeSelect;
                break;
            case MULTIPLE_SELECT:
                selectLocator = multipleSelect;
                break;
        }

        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(selectLocator)));
        select.selectByVisibleText(value);
        logger.info("Following options are selected " +value);

        return this;
    }

    public SelectPage setCalendarField(CalendarField calendarField, LocalDate date) {
        By selectLocator = null;

        switch (calendarField) {
            case FROM -> selectLocator = calendarFrom;
            case TO -> selectLocator = calendarTo;
        }

        sendKeys(driver.findElement(selectLocator), date.format(dateFormat));
        logger.info("Dates are selected");

        return this;
    }

    public SelectPage pressButton() {
        actions.moveToElement(driver.findElement(BUTTON)).click().build().perform();
        logger.info("Button is pressed");
        return this;
    }

    public SelectPage checkH2(String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(h2));
        String actualText = driver.findElement(h2).
                getText();
        Assert.assertEquals(actualText, expectedText, "We expected text: " + actualText + "\nequals " + expectedText);
        logger.info("Courses are checked");
        return this;
    }

    private void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }
}
