package test.objectpoolbrowser;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import driverobjectpoolwithbrowserfactory.BrowserType;
import driverobjectpoolwithbrowserfactory.DriverPool;

public class ObjectPoolBrowserTest {

	DriverPool pool;

	@Test
	public void test1() {
		pool = new DriverPool();
		WebDriver driver = pool.getDriver(BrowserType.CHROME);
		driver.get("https://www.google.com/");
		pool.releaseDriver(BrowserType.CHROME, driver);
	}

	@Test
	public void test2() {
		WebDriver driver = pool.getDriver(BrowserType.CHROME);
		driver.get("https://www.facebook.com/");
		pool.releaseDriver(BrowserType.CHROME, driver);

	}

}
