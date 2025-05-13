package homework20;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json"},
        features = {"src/test/resources/feature"},
        glue = {"lesson20/step_definition"}
)
public class loginPageRunner extends AbstractTestNGCucumberTests{

}
