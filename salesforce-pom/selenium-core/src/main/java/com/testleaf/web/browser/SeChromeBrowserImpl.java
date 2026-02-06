package com.testleaf.web.browser;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeChromeBrowserImpl extends SeBrowserImpl {

	public SeChromeBrowserImpl() {
		super(new ChromeDriver(new ChromeOptions().addArguments("--disable-notifications")));
    }
}
