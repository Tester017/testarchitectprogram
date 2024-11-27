package test.objectpoolbrowser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import objectpooladvanced.IWebDriverObjectPool;
import objectpooladvanced.WebDriverObjectPool;
import webdriverfactoryadvanced.BrowserType;

public class ObjectPoolTest {
	
	IWebDriverObjectPool wdop = new WebDriverObjectPool();
	
	@Test
	public void testObjectPoolChromeWithMaximizeAndHeadless() {
		
		
		List<String> optionsList = Arrays.asList("--start-maximized","--headless");
		Collections.sort(optionsList);

		WebDriver driver = wdop.getDriver(BrowserType.CHROME, optionsList, "https://www.google.com/");
		System.out.println(driver.hashCode());
		wdop.releaseDriver(driver);
		
	}
	
	@Test
	public void testObjectPoolChromeWithMaximize() {

		List<String> optionsList = Arrays.asList("--start-maximized");
		Collections.sort(optionsList);
		System.out.println(optionsList.toString());
		WebDriver driver = wdop.getDriver(BrowserType.CHROME, optionsList, "https://www.facebook.com/");
		System.out.println(driver.hashCode());
		wdop.releaseDriver(driver);
		
	}
	
	@Test
	public void testObjectPoolChromeWithHeadlessAndMaximize() {

		List<String> optionsList = Arrays.asList("--headless","--start-maximized");
		Collections.sort(optionsList);
		System.out.println(optionsList.toString());
		WebDriver driver = wdop.getDriver(BrowserType.CHROME, optionsList, "https://www.facebook.com/");
		System.out.println(driver.hashCode());
		wdop.releaseDriver(driver);


		
	}

}
