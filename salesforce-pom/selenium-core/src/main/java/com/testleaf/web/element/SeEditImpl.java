package com.testleaf.web.element;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeEditImpl extends SeElementImpl implements Edit{

	public SeEditImpl(WebElement element) {
		super(element);
	}

	@Override
	public void type(String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}

	@Override
	public String getValue() {
		return element.getAttribute("value");
	}

	@Override
	public void clear() {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}

	@Override
	public void pressEnter() {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(Keys.ENTER);		
	}

}
