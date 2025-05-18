package homework21;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AndroidApplicationPage {
    AppiumDriver driver;
    WebDriverWait wait;
    Actions actions;

    public AndroidApplicationPage(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    private static final class Locators{
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By listView = AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/*");
        private static final By dataWidgets = AppiumBy.accessibilityId("Date Widgets");
        private static final By dialog = AppiumBy.accessibilityId("1. Dialog");
        private static final By changeTheDate = AppiumBy.accessibilityId("change the date");
        private static final By changeTheTimeSpinner = AppiumBy.accessibilityId("change the time (spinner)");
        private static final By changeTheDateButton = AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
        private static final By hourSpinner = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText");
        private static final By hourButton = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.Button[2]");

        private static final By minutesSpinner = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.EditText");
        private static final By minutesButton = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.Button[2]");

        private static final By amPmSpinner = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.NumberPicker/android.widget.EditText");
        private static final By amPmButton = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.NumberPicker/android.widget.Button");

        private static final By dateTimeDisplay = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"io.appium.android.apis:id/dateDisplay\"]");
        private static final By nextButton = AppiumBy.accessibilityId("Next");
        private static final By tapField = AppiumBy.xpath("(//android.widget.TextView)[2]");

    }
    public void clickOnView(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
    }
    public void clickOnDateWidgets(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dataWidgets)).click();
    }
    public void clickOnDialog(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dialog)).click();
    }
    public void clickOnChangeTheDate(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTheDate)).click();
    }
    public void clickOnChangeTheTimeSpinner(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTheTimeSpinner)).click();
    }

    public int countButtons() {
        boolean found = false;
        HashSet<String> allButtonsTexts = new HashSet<>();

        while (!found){
            List<WebElement> buttons = driver.findElements(Locators.listView);
            for (WebElement button : buttons){
                String text = button.getText();
                if (Objects.equals(text, "WebView3")){
                    found = true;
                }

                allButtonsTexts.add(text);
            }

            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\"android:id/list\")).scrollForward(50)"));
        }

        return allButtonsTexts.size();
    }

    public void pickTheDate(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")
                                                        .withLocale(Locale.US);
        String dateInFormat = date.format(dateFormat);
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"" + dateInFormat + "\"]")).click();
        driver.findElement(Locators.changeTheDateButton).click();
    }

    public void sendKeys(WebElement locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
    }

    public void pickTheTimeSpinner(int hours, int minutes) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.hourSpinner));
        WebElement hourSpinner = driver.findElement(Locators.hourSpinner);
        setSpinnerValue(Locators.hourSpinner, Locators.hourButton, String.format("%02d",  hours));
        setSpinnerValue(Locators.minutesSpinner, Locators.minutesButton, String.format("%02d",  minutes));
        setSpinnerValue(Locators.amPmSpinner, Locators.amPmButton, "PM");
        driver.findElement(Locators.changeTheDateButton).click();
    }

    private void setSpinnerValue(By spinner, By button, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));

        WebElement spinnerElt = driver.findElement(spinner);
        WebElement buttonElt = driver.findElement(button);

        while (!Objects.equals(spinnerElt.getText(), value)) {
            buttonElt.click();
        }
    }

    public void checkDateValue(LocalDateTime expected) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M-dd-yyyy HH:mm");
        String actualDate = driver.findElement(Locators.dateTimeDisplay).getText();
        Assert.assertEquals(actualDate, expected.format(dateFormat));
    }

    public void scrollUntilTextSwitcherAndClick(){
        WebElement element =  driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
        element.click();
    }
    public void tapNextButton() {
        for(int i=0; i<5; i++){
            driver.findElement(Locators.nextButton).click();
        }
        String resultText = driver.findElement(Locators.tapField).getText();
        Assert.assertEquals(resultText, "5");
    }
}
