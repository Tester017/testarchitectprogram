package browserfactory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeBrowser extends Browsers implements IBrowser{
	
	private static final Logger logger = Logger.getLogger(EdgeBrowser.class.getName());


	public WebDriver launchBrower() {

		logger.info("Launching Edge");
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		postLaunchSteps();
		return driver;

	}

}
