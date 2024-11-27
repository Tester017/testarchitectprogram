package com.testleaf.web.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeDropdown extends SeButtonImpl implements Dropdown{

	public SeDropdown(WebElement element) {
		super(element);
	}

	@Override
	public void selectDropdownByText(String text) {
		new Select(element).selectByVisibleText(text);
	}
	
	

}
