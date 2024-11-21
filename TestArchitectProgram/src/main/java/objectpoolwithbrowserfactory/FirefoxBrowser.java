package objectpoolwithbrowserfactory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends DriverInstance implements Browser{
	
	Logger logger = Logger.getLogger(FirefoxBrowser.class.getName());
	
	public WebDriver prepareDriver() {
		logger.info("Creation of Firefox Driver");
		driver = new FirefoxDriver();
		return driver;
	}

	public void closeDriver(WebDriver driver) {
		driver.quit();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

}
