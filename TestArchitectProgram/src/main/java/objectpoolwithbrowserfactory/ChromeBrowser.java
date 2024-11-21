package objectpoolwithbrowserfactory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends DriverInstance implements Browser{
	
	Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

	public WebDriver prepareDriver() {
		logger.info("Creation of Chrome driver");
		driver = new ChromeDriver();
		return driver;
	}

	public void closeDriver(WebDriver driver) {
		driver.quit();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

}
