package com.testleaf.web.browser;

import org.openqa.selenium.chrome.ChromeDriver;

public class SeChromeBrowserImpl extends SeBrowserImpl {
    public SeChromeBrowserImpl() {
        super(new ChromeDriver());
    }
}
