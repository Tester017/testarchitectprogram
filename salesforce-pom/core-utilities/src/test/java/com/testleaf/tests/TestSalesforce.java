package com.testleaf.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.constants.LocatorType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.api.APIClient;
import com.testleaf.web.api.ResponseAPI;
import com.testleaf.web.browser.Browser;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Link;

public class TestSalesforce {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		// Get the browser
		Browser browser = DriverManager.getBrowserWithAPI(BrowserTestEngine.SELENIUM, BrowserType.CHROME);
        APIClient api = (APIClient) browser;

       
		// steps to test
		browser.navigateTo("https://qeagle8-dev-ed.develop.lightning.force.com/");
		
		//login
		browser.locateEdit(LocatorType.ID, "username").type("majay3574@gmail.com");
		browser.locateEdit(LocatorType.ID, "password").type("Ajaymichael@123");
		browser.locateButton(LocatorType.ID, "Login").click();
		
		String firstName = "Saravanan";
        String lastName = "Manickam";
        String companyName = "Testleaf";
        
        String body = "{\n"
        		+ "    \"FirstName\": \""+firstName+"\",\n"
        		+ "    \"LastName\": \""+lastName+"\",\n"
        		+ "    \"Company\": \""+companyName+"\"\n"
        		+ "}";
        
        String token = "00D5g00000LNGD5!AR0AQPcS7aLY.7d47XvlCbmlGORbeom6TctmL9xpFSJMHJRy4AyndOIxFgTRz._sRFuxHx2o39Qn_IchPnsOIszO2vI6FjIH";
        
        ResponseAPI response = api.post("https://qeagle8-dev-ed.develop.my.salesforce.com/services/data/v58.0/sobjects/Lead",token, body);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
        

		// Click Setup
		browser.locateButton(LocatorType.XPATH, "//div[@class='slds-icon-waffle']").click();
		browser.locateButton(LocatorType.XPATH, "//button[@aria-label='View All Applications']").click();
		browser.locateEdit(LocatorType.XPATH, "//input[@placeholder='Search apps or items...']").type("Leads");
		
		// Click Leads
		browser.locateLink(LocatorType.XPATH, "//mark[text()='Leads']").click();
		browser.locateEdit(LocatorType.XPATH, "//input[@placeholder='Search this list...']").type(firstName);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		browser.locateEdit(LocatorType.XPATH, "//input[@placeholder='Search this list...']").pressEnter();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// print the name
		String text = browser.locateLink(LocatorType.XPATH, "(//a[@data-refid='recordId'])[1]").getText();
		System.out.println(text);
		
		// Close the browser
		browser.closeBrowser();

	}

}
