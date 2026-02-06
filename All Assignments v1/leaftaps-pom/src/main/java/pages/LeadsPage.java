package pages;

import com.testleaf.constants.LocatorType;

public class LeadsPage extends BasePage {

    public CreateLeadPage clickCreateLeadLink() {
    	browser.locateLink(LocatorType.LINK_TEXT, "Create Lead").click();
        return new CreateLeadPage();
    }

    public FindLeadPage clickFindLeadsLink() {
    	browser.locateLink(LocatorType.LINK_TEXT, "Find Leads").click();
        return new FindLeadPage();
    }

    public MergeLeadPage clickMergeLeadLink() {
    	browser.locateLink(LocatorType.LINK_TEXT, "Merge Leads").click();
        return new MergeLeadPage();
    }
    
    
}
