package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.Status;
import utils.ApiLogger;

public class ApiStepHooks extends ExtentReportListener {

    @After
    public void logApiData(Scenario scenario) {
        // Retrieve API logs captured during the step
        String requestLog = ApiLogger.getRequestLog();
        String responseLog = ApiLogger.getResponseLog();

        if (requestLog != null && !requestLog.isEmpty()) {
            getTest().log(Status.INFO, "<pre>Request Data:\n" + requestLog + "</pre>");
        }
        if (responseLog != null && !responseLog.isEmpty()) {
            getTest().log(Status.INFO, "<pre>Response Data:\n" + responseLog + "</pre>");
        }
        // Clear logs after reporting to prevent duplication
        ApiLogger.setRequestLog("");
        ApiLogger.setResponseLog("");
        
        
        
    }
}
