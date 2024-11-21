package driverobjectpoolwithbrowserfactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.openqa.selenium.WebDriver;


public class DriverPool extends BrowserFactory implements WebdriverPool {
	
	WebDriver driver;

	Map<BrowserType, Queue<WebDriver>> driverPool;
	
	public DriverPool(){
		driverPool = new HashMap<BrowserType, Queue<WebDriver>>();
	}

	public WebDriver getDriver(BrowserType type) {
		driverPool.putIfAbsent(type, new LinkedList<WebDriver>());
		if (!driverPool.get(type).isEmpty()) {
			driver = driverPool.get(type).poll();
			 System.err.println(driver.hashCode());
			 return driver;
		}
		else {
			driver = createDriver(type);
			 System.err.println(driver.hashCode());
			return driver;
		}
	}

	public void releaseDriver(BrowserType type, WebDriver driver) {
		
		if(driver != null) {
			driverPool.get(type).offer(driver);
		}

	}

	public void closeAllDriver() {
		
		for(Entry<BrowserType, Queue<WebDriver>> type:driverPool.entrySet()) {
			
			for (WebDriver browser :type.getValue()) {
				browser.quit();
			}
			
		}

	}
	
}
