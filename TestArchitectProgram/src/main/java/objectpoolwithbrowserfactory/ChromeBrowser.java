package objectpoolwithbrowserfactory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser{
	
	Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

	public WebDriver prepareDriver() {
		logger.info("Creation of Chrome driver");
		return new ChromeDriver();
	}

	public void closeDriver(WebDriver driver) {
		driver.quit();
	}

}
