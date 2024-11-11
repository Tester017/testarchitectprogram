package browserfactory;

import java.util.logging.Logger;

public class ChromeBrowser implements IBrowser{
	
	private static final Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

	public void launchBrower() {

		logger.info("Launching chrome");
	}

}
