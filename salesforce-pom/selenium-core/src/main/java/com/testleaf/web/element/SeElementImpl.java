package com.testleaf.web.element;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeElementImpl implements Element{
	
	protected WebElement element;
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public SeElementImpl(WebElement element) {
		driver = ((RemoteWebElement)element).getWrappedDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.element = element;
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	@Override
	public boolean isVisible() {
		return element.isDisplayed();
	}

	@Override
	public String getAttribute(String attribute) {
		return element.getAttribute(attribute);
	}
	
	@Override
	public String getText() {
		return element.getText();
	}
	

}
