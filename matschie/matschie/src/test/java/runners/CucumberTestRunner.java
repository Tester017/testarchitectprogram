package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import hooks.ExtentReportListener;

@Listeners(ExtentReportListener.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"steps", "hooks", "context"},
    plugin = {"pretty", "hooks.CucumberStepListener","hooks.CucumberEventListener" }
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
