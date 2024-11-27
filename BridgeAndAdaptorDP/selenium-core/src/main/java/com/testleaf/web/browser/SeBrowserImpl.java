package com.testleaf.web.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.SeButtonImpl;
import com.testleaf.web.element.SeEditImpl;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.SeElementImpl;
import com.testleaf.web.element.SeLinkImpl;
import com.testleaf.web.element.Link;

public class SeBrowserImpl implements Browser{

	private WebDriver driver;
	
	public SeBrowserImpl() {
		this.driver = new ChromeDriver();
	}
	
	@Override
	public void navigateTo(String url) {
		this.driver.get(url);
	}

	@Override
	public void maximize() {
		this.driver.manage().window().maximize();
	}

	@Override
	public void closeBrowser() {
		this.driver.close();
	}
	
	private WebElement findElement(LocatorType locatorType, String locator) {
		switch(locatorType) {
		case ID: return this.driver.findElement(By.id(locator));
		case NAME: return this.driver.findElement(By.name(locator));
		case CLASS: return this.driver.findElement(By.className(locator));
		case XPATH: return this.driver.findElement(By.xpath(locator));
		default: throw new IllegalArgumentException("Unsupported Locator Type :"+locatorType);
		}
	}

	@Override
	public SeElementImpl locateElement(LocatorType locatorType, String Element) {
		return new SeElementImpl(findElement(locatorType, Element));
	}

	@Override
	public Edit locateEdit(LocatorType locatorType, String Element) {
		return new SeEditImpl(findElement(locatorType, Element));
	}

	@Override
	public SeButtonImpl locateButton(LocatorType locatorType, String Element) {
		return new SeButtonImpl(findElement(locatorType, Element));
	}

	@Override
	public Link locateLink(LocatorType locatorType, String Element) {
		return new SeLinkImpl(findElement(locatorType, Element));

	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

}
