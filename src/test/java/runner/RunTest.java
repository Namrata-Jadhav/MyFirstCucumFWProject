package runner;

import Stepdefs.Stepdefs;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:Features",
        glue = "Stepdefs",
       // tags  ="@OpenNewAccount",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        dryRun = false
)
public class RunTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}