package pages;


import com.testleaf.constants.LocatorType;
import base.ProjectHooks;

public class HomePage extends ProjectHooks {

	public HomePage clickAppLauncher() {
		getBrowser().locateButton(LocatorType.XPATH, "//div[@class='slds-icon-waffle']").click();
		return this;
	}
	
	public HomePage clickViewAllApps() {
		getBrowser().locateButton(LocatorType.XPATH, "//button[@aria-label='View All Applications']").click();
		return this;
	}
	
	public HomePage searchApps(String appName) {
		getBrowser().locateEdit(LocatorType.XPATH, "//input[@placeholder='Search apps or items...']").type(appName);
		return this;
	}
	
	public LeadsPage clickLeads() {
		getBrowser().locateLink(LocatorType.XPATH, "//mark[text()='Leads']").click();
		return new LeadsPage();
	}
	
}
