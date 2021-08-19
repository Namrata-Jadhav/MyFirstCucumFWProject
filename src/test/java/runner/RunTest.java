package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:Features",
        glue = "Stepdefs",
        tags  ="@BillPayFeature",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        dryRun = false
)
class RunTest extends AbstractTestNGCucumberTests {

}
