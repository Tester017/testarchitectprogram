package driverobjectpoolwithbrowserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public WebDriver createDriver(BrowserType type)  {

		switch (type) {
		case CHROME:
			return new ChromeDriver();
		case FIREFOX:
			return new FirefoxDriver();

		default:
			throw new IllegalArgumentException("Incorrect browser type is provided");
		}

	}

}
