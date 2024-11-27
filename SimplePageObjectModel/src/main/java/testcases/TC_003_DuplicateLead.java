package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethod;
import pages.LoginPage;

public class TC_003_DuplicateLead extends ProjectSpecificMethod {

	@BeforeTest
	public void setData() {

		dataSheetName = "DuplicateLead";
	}

	@Test(dataProvider = "fetchData")
	public void runDuplicateLead(String uName, String pass, String ph) throws InterruptedException {
		LoginPage lp = new LoginPage();
		lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickfindLeadLink()
				.clickPhoneTab().enterPhoneNumber(ph).clickFindLeadButton().clickFirstLead().clickDuplicateButton()
				.clickCreateDuplicate().verifyLead();

	}
}
