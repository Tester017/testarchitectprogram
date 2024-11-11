package browserfactory;

import java.util.logging.Logger;


public class BrowserHelper {
	
	private static final Logger logger = Logger.getLogger(BrowserHelper.class.getName());
	
	public static void maximise() {
		logger.info("maximising the browser ");
	}
	
	public static void implicitWait() {
		logger.info("setting the driver wait ");
	}
	
	public static void loadUrl() {
		logger.info("loading the URL ");
	}


}
