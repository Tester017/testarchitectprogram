package pages;

import com.testleaf.constants.LocatorType;


public class DuplicateLeadPage extends BasePage {
	
	public ViewLeadPage clickCreateDuplicate() {
		browser.locateButton(LocatorType.NAME, "submitButton").click();		
		return new ViewLeadPage();
	}
}
