package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethod;
import pages.LoginPage;

public class TC_002_EditLead extends ProjectSpecificMethod {

	@BeforeTest
	public void setData() {

		dataSheetName = "EditLead";
	}

	@Test(dataProvider = "fetchData")
	public void runEditLead(String uName, String pass, String ph,String updatecName) throws InterruptedException {
		LoginPage lp = new LoginPage();
		lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickfindLeadLink()
				.clickPhoneTab().enterPhoneNumber(ph).clickFindLeadButton().clickFirstLead().clickEditButton()
				.updateCompanyName(updatecName).clickUpdate().verifyCompanyName();
	}
}
