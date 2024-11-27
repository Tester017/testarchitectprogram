package com.testleaf.drivers.manager;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.web.browser.Browser;
import com.testleaf.web.browser.PwBrowserImpl;
import com.testleaf.web.browser.SeBrowserImpl;

public class DriverManager {

	private static Browser browser;
	
	public static Browser getBrowser(BrowserTestEngine browserEngine) {
		if(browser == null) {
			browser = setupBrowser(browserEngine);
		}
		return browser;
	}

	private static Browser setupBrowser(BrowserTestEngine browserEngine) {
		switch (browserEngine) {
		case SELENIUM: {
			return new SeBrowserImpl();
		}
		case PLAYWRIGHT: {
			return new PwBrowserImpl();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + browserEngine);
		}
		
	}
}
