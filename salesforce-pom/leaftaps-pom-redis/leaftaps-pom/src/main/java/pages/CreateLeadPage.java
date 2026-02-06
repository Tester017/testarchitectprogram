package pages;


import com.testleaf.constants.LocatorType;
import base.ProjectHooks;

public class CreateLeadPage extends ProjectHooks {

	public CreateLeadPage enterCompanyName(String cName) {
		getBrowser().locateEdit(LocatorType.ID, "createLeadForm_companyName").type(cName);
		return this;
	}

	public CreateLeadPage enterFirstName(String fName) {
		getBrowser().locateEdit(LocatorType.ID, "createLeadForm_firstName").type(fName);
		return this;
	}

	public CreateLeadPage enterLastName(String lName) {
		getBrowser().locateEdit(LocatorType.ID, "createLeadForm_lastName").type(lName);
		return this;
	}

	public CreateLeadPage enterPhno(String ph) {
		getBrowser().locateEdit(LocatorType.ID, "createLeadForm_primaryPhoneNumber").type(ph);
		return this;
	}

	public ViewLeadPage clickSubmit() {
        getBrowser().locateButton(LocatorType.NAME, "submitButton").click();
        return new ViewLeadPage();
	}

}
