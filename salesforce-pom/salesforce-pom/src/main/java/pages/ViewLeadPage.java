package pages;

import com.testleaf.constants.LocatorType;

public class ViewLeadPage extends BasePage {

    public ViewLeadPage verifyLead(String expectedLeadId) {
        String actualName = getBrowser().locateElement(LocatorType.ID, "viewLead_companyName_sp").getText();

        if (actualName.contains(expectedLeadId)) {
            System.out.println("Lead ID Verified: " + actualName);
        } else {
            System.out.println("Lead ID Mismatch. Expected: " + expectedLeadId + ", Found: " + actualName);
        }
        return this;
    }

    public ViewLeadPage verifyCompanyName(String expectedCompanyName) {
        String actualCompanyName = getBrowser().locateElement(LocatorType.ID, "viewLead_companyName_sp").getText();

        if (actualCompanyName.contains(expectedCompanyName)) {
            System.out.println("Company Name Verified: " + actualCompanyName);
        } else {
            System.out.println("Company Name Mismatch. Expected to contain: " + expectedCompanyName + ", Found: " + actualCompanyName);
        }
        return this;
    }

    public EditLeadPage clickEditButton() {
        getBrowser().locateLink(LocatorType.LINK_TEXT, "Edit").click();
        return new EditLeadPage();
    }

    public DuplicateLeadPage clickDuplicateButton() {
        getBrowser().locateLink(LocatorType.LINK_TEXT, "Duplicate Lead").click();
        return new DuplicateLeadPage();
    }

    public LeadsPage clickDeleteButton() {
        getBrowser().locateLink(LocatorType.LINK_TEXT, "Delete").click();
        return new LeadsPage();
    }
}
