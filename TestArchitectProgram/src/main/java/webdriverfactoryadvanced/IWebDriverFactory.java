package webdriverfactoryadvanced;

import java.util.List;

import org.openqa.selenium.WebDriver;

public interface IWebDriverFactory {
	
	public WebDriver createDriver(BrowserType browser, List<String> cap);

}
