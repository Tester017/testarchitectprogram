package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class PooledSeBrowserImpl extends SeBrowserImpl {

	private WebDriverPoolCapabilitiesFactory poolFactory;

	public PooledSeBrowserImpl(WebDriver driver, WebDriverPoolCapabilitiesFactory poolFactory, BrowserType browserType,
			Capabilities capabilities) {
		super(driver);
		this.poolFactory = poolFactory;
	}

	@Override
	public void closeBrowser() {
		poolFactory.releaseDriver(driver);
	}
}
