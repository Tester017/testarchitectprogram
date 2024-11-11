package browserfactory;

import java.time.Duration;
import java.util.logging.Logger;


public class BrowserHelper extends Browsers{
	
	private static final Logger logger = Logger.getLogger(BrowserHelper.class.getName());
	
	public static void maximise() {
		logger.info("maximising the browser ");
		driver.manage().window().maximize();
	}
	
	public static void implicitWait() {
		logger.info("setting the driver wait ");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public static void loadUrl() {
		logger.info("loading the URL ");
		driver.get("https://www.google.com/");
	}


}
