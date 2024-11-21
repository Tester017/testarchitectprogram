package objectpooladvanced;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.WebDriver;

import webdriverfactoryadvanced.BrowserType;
import webdriverfactoryadvanced.WebDriverFactory;

public class WebDriverObjectPool implements IWebDriverObjectPool {

	WebDriverFactory wdf;
	ConcurrentMap<String, BlockingQueue<WebDriver>> driverPool;
	ConcurrentMap<WebDriver, String> driverToBrowserKey;

	public WebDriverObjectPool() {
		wdf = new WebDriverFactory();
		driverPool = new ConcurrentHashMap<String, BlockingQueue<WebDriver>>();
		driverToBrowserKey = new ConcurrentHashMap<WebDriver, String>();
	}

	public WebDriver getDriver(BrowserType browser, List<String> cap, String url) {
		System.out.println("BrowserKey is " + browser + cap);
		driverPool.putIfAbsent(browser + cap.toString(), new LinkedBlockingQueue<WebDriver>());
		WebDriver driver = driverPool.get(browser + cap.toString()).poll();
		if (driver == null) {
			driver = wdf.createDriver(browser, cap);
			driverToBrowserKey.put(driver, browser + cap.toString());
		}
		driver.get(url);
		return driver;
	}

	public void releaseDriver(WebDriver driver) {
		if (driver != null) 
			driverPool.get(driverToBrowserKey.get(driver)).offer(driver);
		

	}

}
