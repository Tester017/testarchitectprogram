package webdriverfactoryadvanced;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory implements IWebDriverFactory{

	public WebDriver createDriver(BrowserType browser,List<String> cap) {
		
		switch (browser) {
		case CHROME:
			return new ChromeDriver(new ChromeOptions().addArguments(cap));
		case FIREFOX:
			return new FirefoxDriver(new FirefoxOptions().addArguments(cap));	

		default:
			throw new IllegalArgumentException("Unsupported browserformat");
		}
	}

}
