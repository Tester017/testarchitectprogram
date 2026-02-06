package com.testleaf.web.browser;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import com.testleaf.constants.BrowserType;

public class SeBrowserFactory implements BrowserFactory {

	private static final WebDriverPoolCapabilitiesFactory poolFactory = new WebDriverPoolCapabilitiesFactory(new SeWebDriverFactory());

	public Browser createBrowser(BrowserType browserType) {
        /*switch (browserType) {
            case CHROME:
                return new SeChromeBrowserImpl();
            case FIREFOX:
                return new SeFirefoxBrowserImpl();
            default:
                throw new IllegalArgumentException("Unsupported BrowserType: " + browserType);
        } */
        
        Capabilities capabilities = new MutableCapabilities();
        WebDriver driver = poolFactory.getDriver(browserType, capabilities);
        return new PooledSeBrowserImpl(driver, poolFactory, browserType, capabilities);
    }
}

