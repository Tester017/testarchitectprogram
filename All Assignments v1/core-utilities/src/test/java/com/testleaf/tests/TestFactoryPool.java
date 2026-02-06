package com.testleaf.tests;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class TestFactoryPool {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		testOne();
		testTwo();

	}

	private static void testTwo() throws JsonMappingException, JsonProcessingException {
		// Get the browser
		Browser browser = DriverManager.getBrowserWithAPI(BrowserTestEngine.SELENIUM, BrowserType.CHROME);
        APIClient api = (APIClient) browser;

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
		System.out.println("The title is " + title);

		// Click CRMSFA
		Link crmsfaLink = browser.locateLink(LocatorType.XPATH, "//div[@class='crmsfa']//img");
		crmsfaLink.click();

		// Click on Create lead link
		Link createLeadLink = browser.locateLink(LocatorType.XPATH, "//a[contains(text(),'Create Lead')]");
		createLeadLink.click();

		// Get the company name, first name and last name from API (test data >>
		// Mockaroo)
		// https://api.mockaroo.com/api/5cd160c0?count=1&key=1b952600
		ResponseAPI responseAPI = api.get("https://api.mockaroo.com/api/5cd160c0?count=1&key=1b952600");
		System.out.println(responseAPI.getStatusCode());
		System.out.println(responseAPI.getBody());

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> testdata = objectMapper.readValue(responseAPI.getBody(), Map.class);
		System.out.println(testdata.get("company_name"));
		System.out.println(testdata.get("first_name"));
		System.out.println(testdata.get("last_name"));

		// Find the Company name and enter
		Edit companyName = browser.locateEdit(LocatorType.ID, "createLeadForm_companyName");
		companyName.type(testdata.get("company_name"));

		// Find the First name and enter
		Edit firstName = browser.locateEdit(LocatorType.ID, "createLeadForm_firstName");
		firstName.type(testdata.get("first_name"));

		// Find the Last name and enter
		Edit lastName = browser.locateEdit(LocatorType.ID, "createLeadForm_lastName");
		lastName.type(testdata.get("last_name"));

		// Close the browser
		browser.closeBrowser();

	}

	private static void testOne() throws JsonMappingException, JsonProcessingException {
		// Get the browser
		Browser browser = DriverManager.getBrowser(BrowserTestEngine.SELENIUM, BrowserType.CHROME);

		// load the url
		browser.navigateTo("https://leafground.com");

		// Find the email and enter
		Edit email = browser.locateEdit(LocatorType.ID, "email");
		email.type("babu@testleaf.com");

		// Find the send and click
		Button send = browser.locateButton(LocatorType.XPATH, "//span[text()='Send']");
		send.click();

		// Print the title
		String title = browser.getTitle();
		System.out.println("The title is " + title);
		
		// release the driver
		browser.closeBrowser();

	}

}
