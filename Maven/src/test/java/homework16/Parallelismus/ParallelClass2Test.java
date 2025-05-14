package homework16.Parallelismus;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParallelClass2Test {
    @Description("Checking second parallels of tests")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void parallel6() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(true);
    }

    @Test
    public void parallel7() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(true);
    }

    @Test
    public void parallel8() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(true);
    }

    @Test
    public void parallel9() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(true);
    }

    @Test
    public void parallel10() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(true);
    }
}
