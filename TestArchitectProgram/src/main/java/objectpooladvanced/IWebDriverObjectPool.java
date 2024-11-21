package objectpooladvanced;

import java.util.List;

import org.openqa.selenium.WebDriver;

import webdriverfactoryadvanced.BrowserType;


public interface IWebDriverObjectPool {
	
	public WebDriver getDriver(BrowserType browser, List<String> cap, String url);
	public void releaseDriver(WebDriver driver);

}
