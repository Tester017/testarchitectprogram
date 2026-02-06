package com.dz.core.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.utils.SeleniumUtils;

//import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory {

	static WebDriver driver;

	public enum Browser {
		firefox, chrome, edge, iexplorer, safari, chromeHeadless
	}

	public static WebDriver driverSetup(String oBrowser) {
		try {
			Browser browser = Browser.valueOf(oBrowser);
			driver = createDriver(browser);
			System.err.println("Running in Remote");

			Driver.setWebDriver(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	public static WebDriver createDriver(Browser browser) {
		switch (browser) {
		case chrome:
			return getChromeDriver();
		case edge:
			return getEdgeDriver();
		case iexplorer:
			return getIEDriver();
		case chromeHeadless:
			return getChromeDriverHeadless();
		default:
			throw new RuntimeException("Invalid Browser");
		}
	}

	private static WebDriver getIEDriver() {
		//WebDriverManager.iedriver().setup();
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		ieOptions.setCapability("requireWindowFocus", true);
		ieOptions.takeFullPageScreenshot();
		ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
		ieOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
		ieOptions.setCapability("ignoreProtectedModeSettings", true);

		return new InternetExplorerDriver(ieOptions);

	}

	private static WebDriver getEdgeDriver() {
		//WebDriverManager.edgedriver().setup();
		EdgeOptions edgeOptions = new EdgeOptions();
		return new EdgeDriver(edgeOptions);
	}

	private static WebDriver getChromeDriver() {

		if (Boolean.parseBoolean(SeleniumUtils.SeleniumProps.getProperty("remote"))) {
			ChromeOptions options = new ChromeOptions();
			if (Boolean.parseBoolean(SeleniumUtils.SeleniumProps.getProperty("headless")))
				options.addArguments("--headless");
			options.setCapability("enableVNC", true);
			try {
				return new RemoteWebDriver(new URL(SeleniumUtils.SeleniumProps.getProperty("remoteurl")), options);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.setProperty("webdriver.chrome.driver", "./Resources/BrowserDrivers/chromedriver.exe");
			// WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (Boolean.parseBoolean(SeleniumUtils.SeleniumProps.getProperty("headless")))
				options.addArguments("--headless");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.popups", 0);
			prefs.put("download.default_directory",
					DriverListener.downloadFolder.getAbsolutePath().replaceAll("\\\\.\\\\", "\\\\"));
			options.setExperimentalOption("prefs", prefs);
			
			DesiredCapabilities cap = new DesiredCapabilities().chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			return new ChromeDriver(cap);
		}
	}
	
	private static WebDriver getChromeDriverHeadless() {
		System.setProperty("webdriver.chrome.driver", "./Resources/BrowserDrivers/chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		Map<String,Object> prefs = new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.popups", 0);
		prefs.put("download.default_directory", DriverListener.downloadFolder.getAbsolutePath().replaceAll("\\\\.\\\\", "\\\\"));
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		DesiredCapabilities cap = new DesiredCapabilities().chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(cap);

	}

}
