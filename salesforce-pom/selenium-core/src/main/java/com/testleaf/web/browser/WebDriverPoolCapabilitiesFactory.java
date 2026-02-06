package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class WebDriverPoolCapabilitiesFactory {

    private static final Logger logger = Logger.getLogger(WebDriverPoolCapabilitiesFactory.class.getName());

    private final WebDriverFactory driverFactory;
    private final ConcurrentMap<BrowserCapabilitiesKey, BlockingQueue<WebDriver>> driverPool;
    private final ConcurrentMap<WebDriver, BrowserCapabilitiesKey> driverToBrowserCapabilitiesKey;

    public WebDriverPoolCapabilitiesFactory(WebDriverFactory factory) {
        this.driverFactory = factory;
        this.driverPool = new ConcurrentHashMap<>();
        this.driverToBrowserCapabilitiesKey = new ConcurrentHashMap<>();
    }

    public WebDriver getDriver(BrowserType browserType, Capabilities capabilities) {
        BrowserCapabilitiesKey key = new BrowserCapabilitiesKey(browserType, capabilities);

        BlockingQueue<WebDriver> queue = driverPool.computeIfAbsent(key, k -> new LinkedBlockingQueue<>());

        WebDriver driver = queue.poll();

        if (driver == null) {
            driver = driverFactory.createDriver(browserType, capabilities);
            driverToBrowserCapabilitiesKey.put(driver, key);
            logger.info("Created new driver: " + driver.hashCode() + " for " + key);
        } else {
            logger.info("Reusing existing driver: " + driver.hashCode() + " for " + key);
        }
        return driver;
    }

    public void releaseDriver(WebDriver driver) {
        BrowserCapabilitiesKey key = driverToBrowserCapabilitiesKey.get(driver);

        if (key != null) {
            BlockingQueue<WebDriver> queue = driverPool.get(key);
            if (queue != null) {
                queue.offer(driver);
                logger.info("Released driver: " + driver.hashCode() + " back to pool for " + key);
            } else {
                driver.quit();
                logger.warning("No queue found for key " + key + ". Quit driver: " + driver.hashCode());
            }
        } else {
            driver.quit();
            logger.warning("Driver without key quit: " + driver.hashCode());
        }
    }

    public void closeAllDrivers() {
        for (Map.Entry<BrowserCapabilitiesKey, BlockingQueue<WebDriver>> entry : driverPool.entrySet()) {
            BrowserCapabilitiesKey key = entry.getKey();
            BlockingQueue<WebDriver> queue = entry.getValue();

            while (!queue.isEmpty()) {
                WebDriver driver = queue.poll();
                try {
                    driver.quit();
                    logger.info("Closed driver: " + driver.hashCode() + " for " + key);
                } catch (Exception e) {
                    logger.severe("Error closing driver: " + driver.hashCode() + " for " + key);
                    e.printStackTrace();
                }
            }
        }

        driverPool.clear();
        driverToBrowserCapabilitiesKey.clear();
    }
}
