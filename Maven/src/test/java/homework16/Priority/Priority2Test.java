package homework16.Priority;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Priority2Test {
    @Description("Checking priorities of tests")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = {"b"})
    public void a() {
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"c"})
    public void b() {
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"d"})
    public void c() {
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"e"})
    public void d() {
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"f"})
    public void e() {
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"g"})
    public void f() {
        Assert.assertTrue(true);
    }
    @Test
    public void g() {
        Assert.assertTrue(true);
    }
}
