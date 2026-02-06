package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.browser.Browser;

import utility.ReadExcel;

public class ProjectHooks {

    public String leadID;
    public String dataSheetName;

    public static Browser browser;
    
	
    @BeforeMethod
    @Parameters({"browserEngine", "browserType"})
    public void preCondition(@Optional("SELENIUM") String browserEngineParam, @Optional("CHROME") String browserTypeParam) {
        BrowserTestEngine browserEngine = BrowserTestEngine.valueOf(browserEngineParam.toUpperCase());
        BrowserType browserType = BrowserType.valueOf(browserTypeParam.toUpperCase());

        // Get the browser instance from DriverManager
        browser = DriverManager.getBrowser(browserEngine, browserType);
     
        // Navigate to the URL and maximize
        browser.navigateTo("http://leaftaps.com/opentaps/");
        browser.maximize();
    }

    @AfterMethod
    public void postCondition() {
        // Close the browser
    	browser.closeBrowser();
    	
    }

    @DataProvider(name = "fetchData")
    public String[][] fetchData() throws IOException {
        return ReadExcel.readExcel(dataSheetName);
    }
}
