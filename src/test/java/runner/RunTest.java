package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:Features/",
        glue = "Stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        dryRun = false
)
public class RunTest extends AbstractTestNGCucumberTests {

}
