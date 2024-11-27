package com.testleaf.web.browser;

import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Element;
import com.testleaf.web.element.Link;

public interface Browser {
	
	// Core methods in the Browser
	void navigateTo(String url);
	void maximize();
	void closeBrowser();
	String getTitle();
	
	// method >> locate Element !!
	Element locateElement(LocatorType locatorType, String Element);
	Edit locateEdit(LocatorType locatorType, String Element);
	Button locateButton(LocatorType locatorType, String Element);
	Link locateLink(LocatorType locatorType, String Element);

}
