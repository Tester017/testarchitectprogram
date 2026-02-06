package pages;

import com.testleaf.constants.LocatorType;


public class DuplicateLeadPage extends BasePage {
	
	public ViewLeadPage clickCreateDuplicate() {
		getBrowser().locateButton(LocatorType.NAME, "submitButton").click();		
		return new ViewLeadPage();
	}
}
