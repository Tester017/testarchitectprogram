package webdriverfactoryadvanced;

import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements IBrowserFactory {

	Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

	public WebDriver launchBrowser(Capabilities cap) {

		ChromeOptions options;
		if (cap instanceof ChromeOptions) {
			options = (ChromeOptions) cap;
			logger.info("chrome is launching with provided options");
		} else {
			options = new ChromeOptions();
			logger.warning("chrome is launching with default options");
		}
		return new ChromeDriver(options);
	}

}
