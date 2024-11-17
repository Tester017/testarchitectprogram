package objectpoolwithbrowserfactory;

public class BrowserFactory {

	public Browser createDriver(BrowserType type)  {

		switch (type) {
		case CHROME:
			return new ChromeBrowser();
		case FIREFOX:
			return new FirefoxBrowser();

		default:
			throw new IllegalArgumentException("Incorrect browser type is provided");
		}

	}

}
