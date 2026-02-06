package com.testleaf.web.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeButtonImpl extends SeElementImpl implements Button{
	
	
	public SeButtonImpl(WebElement element) {
		super(element);
	}

	@Override
	public void click() {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	
}
