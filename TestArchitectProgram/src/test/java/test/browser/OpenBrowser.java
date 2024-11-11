package test.browser;

import org.testng.annotations.Test;

import browserfactory.Browser;
import browserfactory.BrowserFactory;

public class OpenBrowser {

	@Test
	public void testBrowser() {

		BrowserFactory.launchBrowser(Browser.CHROME).launchBrower();
	}

}
