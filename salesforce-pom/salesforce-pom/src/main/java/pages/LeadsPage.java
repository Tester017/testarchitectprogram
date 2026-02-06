package pages;

import com.testleaf.constants.LocatorType;

public class LeadsPage extends BasePage {

    public LeadsPage searchLead(String leadDetails) {
    	getBrowser().locateEdit(LocatorType.XPATH, "//input[@placeholder='Search this list...']").type(leadDetails);
    	getBrowser().locateEdit(LocatorType.XPATH, "//input[@placeholder='Search this list...']").pressEnter();
    	return this;
    }
    
	public String getFirstLead() {
    	return getBrowser().locateLink(LocatorType.XPATH, "(//a[@data-refid='recordId'])[1]").getText();
    }
}
