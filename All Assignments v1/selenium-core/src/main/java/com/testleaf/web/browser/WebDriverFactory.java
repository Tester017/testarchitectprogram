package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver createDriver(BrowserType browserType, Capabilities capabilities);
}