package webdriverfactoryadvanced;

import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser implements IBrowserFactory {

	Logger logger = Logger.getLogger(FirefoxBrowser.class.getName());

	public WebDriver launchBrowser(Capabilities cap) {

		FirefoxOptions options;
		if (cap instanceof FirefoxOptions) {
			options = (FirefoxOptions) cap;
			logger.info("Firefox is launching with provided options");
		} else {
			options = new FirefoxOptions();
			logger.warning("Firefox is launching with default options");
		}
		return new FirefoxDriver(options);
	}

}
