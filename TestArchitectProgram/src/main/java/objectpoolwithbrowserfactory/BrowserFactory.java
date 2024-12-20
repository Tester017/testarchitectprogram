package objectpoolwithbrowserfactory;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

	public WebDriver createDriver(BrowserType type)  {

		switch (type) {
		case CHROME:
			return new ChromeBrowser().prepareDriver();
		case FIREFOX:
			return new FirefoxBrowser().prepareDriver();

		default:
			throw new IllegalArgumentException("Incorrect browser type is provided");
		}

	}

}
