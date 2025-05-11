package homework17;

import homework18.testCases.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverSetUp;



public class DragAndDropTestRefactor {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static DragAndDrop dragAndDrop;

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        loginPage = new LoginPage(driver);
        dragAndDrop = new DragAndDrop(driver);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
    @Test
    public void DragAndDrop() {
        loginPage
                .openLogInPage()
                .fillEmail("aliceperkins@mail.com")
                .fillPassword("96ALiceperkINS71")
                .clickOnButton();
        dragAndDrop.selectDragAndDropPage()
                .openDragAndDropPage()
                .performDragAndDropTask()
                .checkSuccessMessage("Congratulations! Let's test for the best!");

    }
}