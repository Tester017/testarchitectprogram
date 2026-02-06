package com.dz.core.driver;

import org.openqa.selenium.WebDriver;

public class Driver {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void setWebDriver(WebDriver oDriver) {

		driver.set(oDriver);
	}

	public static WebDriver getWebDriver() {
		return driver.get();
	}

	public static boolean isWebDriverEmpty() {

		return driver.get() == null;
	}

	public static void quitDriver() {
		if (!isWebDriverEmpty()) {
			driver.get().quit();
		}
	}

}
