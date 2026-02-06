package come.salesforce.test;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.constants.LocatorType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.browser.Browser;

public class createLeadTest {

	public static void main(String[] args) {
		
		Browser browser = DriverManager.getBrowserWithAPI(BrowserTestEngine.SELENIUM,BrowserType.CHROME);
		
		
		
		browser.navigateTo("https://qeagle8-dev-ed.develop.lightning.force.com/");
		
		browser.locateEdit(LocatorType.ID, "username").type("majay3574@gmail.com");
		browser.locateEdit(LocatorType.ID, "password").type("Ajaymichael@123");
		
		browser.locateButton(LocatorType.ID, "Login").click();
	}
	
	

}
