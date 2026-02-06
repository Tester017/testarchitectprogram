package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethod;
import pages.LoginPage;

public class TC_001_CreateLead extends ProjectSpecificMethod {

	@BeforeTest
	public void setData() {

		dataSheetName = "CreateLead";
	}

	@Test(dataProvider = "fetchData")
	public void runCreateLead(String uName, String pass, String cName, String fName, String lName, String ph) {
		LoginPage lp = new LoginPage();
		lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickCreateLeadLink()
				.enterCompanyName(cName).enterFirstName(fName).enterLastName(lName).enterPhno(ph).clickSubmit()
				.verifyLead();

	}
}
