package pages;

import com.testleaf.constants.LocatorType;

public class EditLeadPage extends BasePage {

    public EditLeadPage updateCompanyName(String updateCName) {
        getBrowser().locateEdit(LocatorType.ID, "updateLeadForm_companyName").type(updateCName);
        return this;
    }

    public ViewLeadPage clickUpdate() {
        getBrowser().locateButton(LocatorType.NAME, "submitButton").click();
        return new ViewLeadPage();
    }
}
