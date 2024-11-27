package com.testleaf.tests;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.LocatorType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.browser.Browser;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Link;

public class TestBrowserEngines {
	
	public static void main(String[] args) {
		
		// Get the browser
		Browser browser = DriverManager.getBrowser(BrowserTestEngine.PLAYWRIGHT);
		
		// load the url
		browser.navigateTo("http://leaftaps.com/opentaps");
		
		// Find the user name and enter
		Edit username = browser.locateEdit(LocatorType.ID, "username");
		username.type("demosalesmanager");
		
		// Find the password and enter
		Edit password = browser.locateEdit(LocatorType.ID, "password");
		password.type("crmsfa");
		
		// Find the login button and click
		Button login = browser.locateButton(LocatorType.CLASS, "decorativeSubmit");
		login.click();
		
		// Print the title
		String title = browser.getTitle();
		System.out.println("The title is "+title);
		
		Link locateLink = browser.locateLink(LocatorType.XPATH, "//a[contains(text(),'CRM/SFA')]");
		locateLink.click();
		
		Link createLeadLink = browser.locateLink(LocatorType.XPATH, "//a[text()='Create Lead']");
		createLeadLink.click();
		
		// Close the browser
//		browser.closeBrowser();
		
	}

}
