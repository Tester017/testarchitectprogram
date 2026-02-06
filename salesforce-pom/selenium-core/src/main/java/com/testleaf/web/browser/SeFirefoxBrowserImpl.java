package com.testleaf.web.browser;

import org.openqa.selenium.firefox.FirefoxDriver;

public class SeFirefoxBrowserImpl extends SeBrowserImpl {
    public SeFirefoxBrowserImpl() {
        super(new FirefoxDriver());
    }
}
