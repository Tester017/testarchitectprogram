package browserfactory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeBrowser extends Browsers implements IBrowser{
	
	private static final Logger logger = Logger.getLogger(ChromeBrowser.class.getName());
	
	public WebDriver launchBrower() {

		logger.info("Launching chrome");
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		postLaunchSteps();
		return driver;
	}

}
