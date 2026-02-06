package pages;

import com.testleaf.constants.LocatorType;
import base.ProjectHooks;

public class WelcomePage extends ProjectHooks {

	public HomePage clickCrmsfa() {
		getBrowser().locateLink(LocatorType.XPATH, "//a[contains(text(),'CRM/SFA')]").click();
		return new HomePage();
	}

	public HomePage clickLogout() {
		getBrowser().locateLink(LocatorType.XPATH, "//input[@value='Logout']").click();
		return new HomePage();
	}
}
