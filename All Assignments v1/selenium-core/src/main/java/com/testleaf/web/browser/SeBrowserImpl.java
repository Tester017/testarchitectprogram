package com.testleaf.web.browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.SeButtonImpl;
import com.testleaf.web.element.SeEditImpl;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.SeElementImpl;
import com.testleaf.web.element.SeLinkImpl;
import com.testleaf.web.element.decorated.SeButtonDecorator;
import com.testleaf.web.element.Link;

public class SeBrowserImpl implements Browser {

	protected WebDriver driver;

	public SeBrowserImpl(WebDriver driver) {
		this.driver = driver;
		//this.driver = new ChromeDriver();
	}

	@Override
	public void navigateTo(String url) {
		this.driver.get(url);
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
		switch (locatorType) {
		case ID:
			return this.driver.findElement(By.id(locator));
		case NAME:
			return this.driver.findElement(By.name(locator));
		case CLASS:
			return this.driver.findElement(By.className(locator));
		case LINK_TEXT:
			return this.driver.findElement(By.linkText(locator));
		case XPATH:
			return this.driver.findElement(By.xpath(locator));
		default:
			throw new IllegalArgumentException("Unsupported Locator Type :" + locatorType);
		}
	}

	@Override
	public SeElementImpl locateElement(LocatorType locatorType, String element) {
		return new SeElementImpl(findElement(locatorType, element));
	}

	@Override
	public Edit locateEdit(LocatorType locatorType, String element) {
		return new SeEditImpl(findElement(locatorType, element));
	}

	@Override
	public Button locateButton(LocatorType locatorType, String element) {
		//new SeButtonImpl(findElement(locatorType, Element));
		Button button = new SeButtonImpl(findElement(locatorType, element));
	    return new SeButtonDecorator(button);
	}

	@Override
	public Link locateLink(LocatorType locatorType, String element) {
		return new SeLinkImpl(findElement(locatorType, element));

	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

}
