package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeWebDriverFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriver(BrowserType browserType, Capabilities capabilities) {
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (capabilities != null) {
                    chromeOptions.merge(capabilities);
                }
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (capabilities != null) {
                    firefoxOptions.merge(capabilities);
                }
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Unsupported BrowserType: " + browserType);
        }
    }

 
}
