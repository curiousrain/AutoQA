import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriver {
    public class DriverSetUp {
        private static org.openqa.selenium.WebDriver driver;

        private static org.openqa.selenium.WebDriver setUpDriver() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        }

        private static boolean isDriverInvalid() {
            try {
                return driver == null || ((RemoteWebDriver) driver).getSessionId() == null;
            } catch (Exception e) {
                return true;
            }
        }

        private static org.openqa.selenium.WebDriver getInstance() {
            if (isDriverInvalid()) {
                driver = setUpDriver();
            }
            return driver;
        }

        public static org.openqa.selenium.WebDriver getDriver(){
            driver = getInstance();
            return driver;
        }
    }
}
