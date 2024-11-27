package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethod;
import pages.DeleteLeadPage;
import pages.FindLeadPage;
import pages.LoginPage;
import pages.MergeLeadPage;

public class TC_004_DeleteLead extends ProjectSpecificMethod {
	@BeforeTest
	public void setData() {

		dataSheetName = "DeleteLead";
	}

	@Test(dataProvider = "fetchData")
	public void runDeleteLead(String uName, String pass, String ph) throws InterruptedException {
		
		String firstLead = new LoginPage().enterUsername(uName).enterPassword(pass).clickLogin()
		.clickCrmsfa().clickLeadsTab()
		.clickfindLeadLink()
		.clickPhoneTab()
		.enterPhoneNumber(ph)
		.clickFindLeadButton()
		.getFirstLead();
		new FindLeadPage()
		.clickDeleteFirstLead()
				.clickfindLeadLink()
				.clickLeadIDTab()
				.enterLeadID(firstLead)
				.clickFindLeadButton()
				.verifydeletedLeadID();

	}
}
