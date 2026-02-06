package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentReportUtils;

public class ScenarioHooks extends ExtentReportListener {
    public static ThreadLocal<String> scenarioName = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioName.set(scenario.getName());
        ExtentReportUtils.updateTestName(getTest(), scenario.getName());
    }
}
