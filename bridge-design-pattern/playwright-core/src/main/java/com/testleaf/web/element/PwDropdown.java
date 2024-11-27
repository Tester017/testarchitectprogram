package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

public class PwDropdown extends PwElementImpl  implements Dropdown{

	public PwDropdown(Locator locator) {
		super(locator);
	}

	@Override
	public void selectDropdownByText(String text) {
		
		locator.selectOption(text);
	}


}
