package hooks;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.PickleStepTestStep;
import com.aventstack.extentreports.ExtentTest;

public class CucumberStepListener extends ExtentReportListener implements ConcurrentEventListener {

	@Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, event -> {
        	if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
                String stepText = testStep.getStep().getText();

                // Map Cucumber status to ExtentReports status
                io.cucumber.plugin.event.Status cucumberStatus = event.getResult().getStatus();
                com.aventstack.extentreports.Status extentStatus;
                if (cucumberStatus == io.cucumber.plugin.event.Status.PASSED) {
                    extentStatus = com.aventstack.extentreports.Status.PASS;
                } else if (cucumberStatus == io.cucumber.plugin.event.Status.FAILED) {
                    extentStatus = com.aventstack.extentreports.Status.FAIL;
                } else if (cucumberStatus == io.cucumber.plugin.event.Status.SKIPPED) {
                    extentStatus = com.aventstack.extentreports.Status.SKIP;
                } else {
                    extentStatus = com.aventstack.extentreports.Status.INFO;
                }

                // Create a child node for the step under the current scenario
                ExtentTest stepNode = getTest().createNode(stepText);
                if (cucumberStatus == io.cucumber.plugin.event.Status.FAILED) {
                    stepNode.log(extentStatus, "Step failed: " + event.getResult().getError());
                } else {
                    stepNode.log(extentStatus, "Step " + cucumberStatus.name().toLowerCase());
                }
            }
        });
    }
}
