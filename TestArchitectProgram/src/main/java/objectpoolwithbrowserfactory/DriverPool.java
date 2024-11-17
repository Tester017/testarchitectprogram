package objectpoolwithbrowserfactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.openqa.selenium.WebDriver;


public class DriverPool extends BrowserFactory implements WebdriverPool {

	Map<BrowserType, Queue<Browser>> driverPool = new HashMap<BrowserType, Queue<Browser>>();

	public WebDriver getDriver(BrowserType type) {
		driverPool.putIfAbsent(type, new LinkedList<Browser>());
		if (!driverPool.get(type).isEmpty()) {
			return (WebDriver) driverPool.get(type).poll();
		}
		else {
			return (WebDriver) createDriver(type);
		}
	}

	public void releaseDriver(BrowserType type, WebDriver driver) {
		
		if(driver != null) {
			driverPool.get(type).offer(driver);
		}

	}

	public void closeAllDriver() {
		
		for(Entry<BrowserType, Queue<Browser>> type:driverPool.entrySet()) {
			
			for (Browser browser :type.getValue()) {
				browser.closeDriver(null);
			}
			
		}

	}

}
