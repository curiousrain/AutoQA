package homework16.Groups;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupsTest {
    @Test(groups = {"group-one"})
    public void one() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-two"})
    public void two() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-one"})
    public void three() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-two"})
    public void four() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-one"})
    public void five() {Assert.assertTrue(true);}
    @Test(groups = {"group-two"})
    public void six() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-one"})
    public void seven() {
        Assert.assertTrue(true);
    }
    @Test(groups = {"group-two"})
    public void eight() {
        Assert.assertTrue(true);
    }

}
