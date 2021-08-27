package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:Features",
        glue = "Stepdefs",
       // tags  ="@OpenNewAccount",
        plugin = {"pretty", "html:target/cucumber-reports.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false
)
public class RunTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {

        return super.scenarios();
    }


}