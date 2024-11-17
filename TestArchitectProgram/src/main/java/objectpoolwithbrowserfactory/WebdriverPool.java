package objectpoolwithbrowserfactory;

import org.openqa.selenium.WebDriver;

public interface WebdriverPool {
	
	public WebDriver getDriver(BrowserType type);
	public void releaseDriver(BrowserType type, WebDriver driver);
	public void closeAllDriver();

}
