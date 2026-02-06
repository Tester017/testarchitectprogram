package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LeadsPage;

public class TC002_EditLead extends BasePage {

	@BeforeTest
	public void setData() {
		dataSheetName = "EditLead";
	}

	@Test(dataProvider = "fetchData")
	public void runEditLead(String ph,String updatedName) throws InterruptedException {

		new LeadsPage()
		.clickFindLeadsLink()
		.clickPhoneTab()
		.enterPhoneNumber(ph)
		.clickFindLeadButton()
		.clickFirstLead()
		.clickEditButton()
		.updateCompanyName(updatedName)
		.clickUpdate()
		.verifyCompanyName(updatedName);
	}
}
